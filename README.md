

# second branch



<img src="https://user-images.githubusercontent.com/63637706/97784423-d6301900-1be1-11eb-96a2-46342add9858.gif" width="300" height="600"/>



## 1. Package

<img src="https://user-images.githubusercontent.com/63637706/97772628-3b591f80-1b8c-11eb-9758-e750d9100d18.PNG">  



## 2. Dependencies

<img src="https://user-images.githubusercontent.com/63637706/97772629-3c8a4c80-1b8c-11eb-890b-53017533f6a7.PNG">







## 3. Theme

<img src="https://user-images.githubusercontent.com/63637706/97772630-3c8a4c80-1b8c-11eb-89e9-86ae135e1a89.PNG">  



## 4. Structure

<div style="text-align : center">
    <img src="https://user-images.githubusercontent.com/63637706/97773577-5334a180-1b94-11eb-9011-e3b1c27709a6.PNG">
    <img src="https://user-images.githubusercontent.com/63637706/97773575-516ade00-1b94-11eb-80cb-e930c0b4691a.PNG">
</div>







## 5. Flow

 * recycler : HomeActivity -> RecyclerActivity
 * fab_layout_change : LinearLayoutManager <-> GridLayoutManager
 * itemClick : RecyclerActivity -> DetailActivity



## 6. Database

```kotlin
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    companion object {
        @Volatile private var INSTANCE : UserDatabase? = null

        fun getDatabase(context : Context) : UserDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java, "userDb"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun userDao() : UserDao
}
```

```kotlin
@Dao
interface UserDao {
    @Query("SELECT * from userDb")
    fun getAll() : LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : User)

    @Delete
    suspend fun deleteUser(user : User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateIdx(users : List<User>)
}
```

```kotlin
@Parcelize
@Entity(tableName = "userDb")
data class User (
    @PrimaryKey(autoGenerate = true)
    var idx : Int,
    val name : String,
    val id : String,
    val password : String,
    val part : String = "android",
    val date : String = SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis())
) : Parcelable
```



## 7. RecyclerActivity

```kotlin
override fun onStart() {
    super.onStart()

    recyclerViewModel.allUsers.observe(this, Observer { users ->
        users?.let {
            adapter.setUsers(it)
            recyclerViewModel.resetList(userList, users)
        }
    })

    recyclerViewModel.layoutItem.observe(this, Observer { layoutItem ->
        layoutItem?.let {
            adapter.setLayout(it)
            recyclerViewModel.changeLayoutManager(rv_recycler)
            rv_recycler.adapter = adapter
        }
    })
}
```



## 8. RecyclerAdapter for data binding

```kotlin
class RecyclerAdapter<B : ViewDataBinding>(private val context : Context) : RecyclerView.Adapter<RecyclerAdapter<B>.VHolder<B>>(){

    private var users = emptyList<User>()
    var layoutItem = R.layout.item_recycler_linear

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) =
        VHolder<B>(LayoutInflater.from(parent.context).inflate(layoutItem, parent,false))

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder : VHolder<B>, position : Int) {
        holder.bind(users[position])
    }

    internal fun setUsers(users : List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    internal fun setLayout(layoutItem : Int) {
        this.layoutItem = layoutItem
    }

    inner class VHolder<B : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding : B = DataBindingUtil.bind(itemView)!!

        fun bind(user: User) {
            binding.setVariable(BR.user, user)
            itemView.setOnClickListener{
                context.startActivityWithUser<DetailActivity>(user)
            }
        }
    }
}
```



## 9. RecyclerViewModel for database

```kotlin
fun init(dao : UserDao) {
    userDao = dao
    allUsers = userDao.getAll()
}

fun delete(position : Int) = viewModelScope.launch(Dispatchers.IO) {     
    userDao.deleteUser(allUsers.value!![position])
}

fun update(users : List<User>) = viewModelScope.launch(Dispatchers.IO) {
    userDao.updateIdx(users)
}

fun resetList(userList : MutableList<User>, users : List<User>) {
    userList.clear()
    userList.addAll(users)
}
```



## 10. RecyclerViewModel for layout changing

```kotlin
fun changeLayout() {
    if(_layoutItem.value==R.layout.item_recycler_linear) _layoutItem.value = R.layout.item_recycler_grid
    else _layoutItem.value = R.layout.item_recycler_linear
}

fun changeLayoutManager(recyclerView : RecyclerView) {   
    if(_layoutItem.value==R.layout.item_recycler_linear) 			
   		recyclerView.layoutManager=LinearLayoutManager(recyclerView.context)
    else recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 3)
}
```



## 11. ItemTouchHelper

```kotlin
fun RecyclerView.itemTouchHelper(recyclerViewModel : RecyclerViewModel, userList : MutableList<User>) {

    val adapter = this.adapter!!

    val itemTouchHelper = ItemTouchHelper(
        object : ItemTouchHelper.SimpleCallback((
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END),
            ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView : RecyclerView,
                from : ViewHolder,
                to : ViewHolder
            ) : Boolean {
                val fromPosition = from.adapterPosition
                val toPosition = to.adapterPosition
                if (fromPosition < toPosition) {
                    for (i in fromPosition until toPosition) {
                        Collections.swap(userList, i, i+1)
                        val fromIdx = userList[i].idx
                        val toIdx = userList[i+1].idx
                        userList[i].idx = toIdx
                        userList[i+1].idx = fromIdx
                    }
                } else {
                    for (i in fromPosition downTo toPosition+1){
                        Collections.swap(userList, i, i-1)
                        val fromIdx = userList[i].idx
                        val toIdx = userList[i-1].idx
                        userList[i].idx = toIdx
                        userList[i-1].idx = fromIdx
                    }
                }
                adapter.notifyItemMoved(fromPosition, toPosition)
                return false
            }

            override fun onSwiped(viewHolder: ViewHolder, swipeDir: Int) {
                val swipedPosition = viewHolder.adapterPosition
                recyclerViewModel.delete(swipedPosition)
                adapter.notifyItemRemoved(swipedPosition)
            }

            override fun clearView(recyclerView: RecyclerView, viewHolder: ViewHolder) {
                super.clearView(recyclerView, viewHolder)
                recyclerViewModel.update(userList)
            }
        })
    itemTouchHelper.attachToRecyclerView(this)
}
```



## 12. DetailActivity for data binding

```kotlin
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.user = intent.getParcelableExtra("user")
    }
}
```

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>
        <variable
            name="user"
            type="com.kimym.onsopt.room.User"/>
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        ..>
	    <TextView
            ..
            android:text="@{user.id}"
            ../>
    
        <TextView
            ..
            android:text="@{user.name}"
            ../>

        <TextView
            ..
            android:text="@{user.part}"
            ../>

        <TextView
            ..
            android:text="@{user.date}"
            ../>
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```



## 13. startActivityWithUser

```kotlin
inline fun <reified T : Activity> Context.startActivityWithUser(user : User){
    val intent = Intent(this, T ::class.java)
    intent.putExtra("user", user)
    startActivity(intent)
}
```