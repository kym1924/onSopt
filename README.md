

# third branch



<div style="text-align : center">
    <img src="https://user-images.githubusercontent.com/63637706/98773282-f0f95d80-242b-11eb-9fa8-5d107b3dfc35.gif" width="250" height="500">
    <img src="https://user-images.githubusercontent.com/63637706/98773286-f48ce480-242b-11eb-9cd7-9464b00bb545.gif" width="250" height="500">
    <img src="https://user-images.githubusercontent.com/63637706/98773954-51d56580-242d-11eb-847e-1b3f3b83dd3f.gif" width="250" height="500">
</div>






## 1. Package

<img src="https://user-images.githubusercontent.com/63637706/98892157-15fcd780-24e3-11eb-85cf-5bf96bb496b8.PNG">  



## 2. Dependencies

<img src="https://user-images.githubusercontent.com/63637706/98620592-e74af980-2348-11eb-936a-48fdc15ad5c5.PNG">







## 3. Theme

<img src="https://user-images.githubusercontent.com/63637706/97772630-3c8a4c80-1b8c-11eb-89e9-86ae135e1a89.PNG">  



## 4. Structure

<div style="text-align : center">
    <img src="https://user-images.githubusercontent.com/63637706/98759958-86d5be00-2415-11eb-9d81-ec76d6448c88.png">
    <img src="https://user-images.githubusercontent.com/63637706/98759960-876e5480-2415-11eb-8cd6-55896ba56008.png">
    <img src="https://user-images.githubusercontent.com/63637706/98759959-876e5480-2415-11eb-8aa0-61344a2ebbc8.png">
    <img src="https://user-images.githubusercontent.com/63637706/98759955-86d5be00-2415-11eb-9c6d-8d247c4dfd51.png">
    <img src="https://user-images.githubusercontent.com/63637706/98759952-85a49100-2415-11eb-83cf-bd239ac83c23.png">
</div>








## 5. Flow

 * main : MainActivity = { HomeFragment, RecyclerFragment, MyPageFragment }
 * home : HomeFragment = { InfoFragment, OtherFragment }
 * signIn : SignInActivity -> HomeFragment
 * vpMain : HomeFragment <-> RecyclerFragment <-> MyPageFragment
 * vpHome : InfoFragment <-> OtherFragment
 * fab_logout : MyPageFragment <-> SignInActivity



## 6. UserDao

```kotlin
...

@Query("SELECT * from userDb WHERE id = :id")
fun getMy(id : String) : User

@Query("SELECT name from userDb WHERE id = :id")
fun getMyName(id : String) : String

@Query("SELECT * from userDb ORDER BY idx DESC LIMIT 1")
fun fromSignUp() : User

@Query("SELECT password from userDb WHERE id = :id")
fun login(id : String) : String
```



## 7. BindingAdapter for SignUpActivity

```kotlin
object SignUpBinding {
    @BindingAdapter("passwordCheck")
    @JvmStatic
    fun passwordCheck(view: View, isSamePassword: Boolean) {
        view.visibility = if (isSamePassword) View.VISIBLE else View.INVISIBLE
    }

    @BindingAdapter("ifActive")
    @JvmStatic
    fun ifActive(view : View, content : String) {
        when (content) {
            "" -> view.setBackgroundResource(R.drawable.background_edit_text)
            else -> view.setBackgroundResource(R.drawable.background_edit_text_active)
        }
    }
}
```

```kotlin
...
binding.etPw.textChangedListener { signUpViewModel.checkPassword() }

binding.etPwCheck.textChangedListener { signUpViewModel.checkPassword() }

signUpViewModel.isValid.observe(this, Observer { isValid ->
    isValid.let { if(isValid) signUpViewModel.insert() else ... }
})
...
```



## 7-1. SignUpViewModel

```kotlin
...

val name = MutableLiveData<String>("")
val id = MutableLiveData<String>("")
val password = MutableLiveData<String>("")
val passwordCheck = MutableLiveData<String>("")

private val _isSamePassword = MutableLiveData<Boolean>(false)
val isSamePassword : LiveData<Boolean>
    get() = _isSamePassword
...

fun checkPassword() {
    _isSamePassword.value = (password.value == passwordCheck.value && !password.value.isNullOrEmpty())
}
```



## 7-2. activity_sign_up.xml

```XML
		...
        
		<EditText
            ...
            android:text="@={signUpViewModel.name}"
            ifActive="@{signUpViewModel.name}"
            ... />

        ...

        <EditText
            ...
            android:text="@={signUpViewModel.id}"
            ifActive="@{signUpViewModel.id}"
            ... />

        ...

        <EditText
            ...
            android:text="@={signUpViewModel.password}"
            ifActive="@{signUpViewModel.password}"
            ... />

        ...

        <EditText
            ...
            android:text="@={signUpViewModel.passwordCheck}"
            ifActive="@{signUpViewModel.passwordCheck}"
            ... />

        <ImageView
            ...
            passwordCheck="@{signUpViewModel.isSamePassword}"
            ... />

        ... />
    </androidx.constraintlayout.widget.ConstraintLayout>
```



## 8. BindingAdapter for SignInActivity

```kotlin
object SignInBinding {
    @BindingAdapter("fromSignUp")
    @JvmStatic
    fun fromSignUp(editText : EditText, content : String?) {
        content?.let{editText.setText(content)}
    }
}
```

```kotlin
...
override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if(requestCode == 1 && resultCode == Activity.RESULT_OK)
        signInViewModel.fromSignUp()
}
...
```



## 8-1. SignInViewModel

```kotlin
...

val id = MutableLiveData<String>("")
val password = MutableLiveData<String>("")

private val _fromSignUp = MutableLiveData<User>()
val fromSignUp : LiveData<User>
    get() = _fromSignUp
...

fun fromSignUp() = viewModelScope.launch(Dispatchers.IO) {       
    _fromSignUp.postValue(userDao.fromSignUp())
}
```



## 8-2. activity_sign_in.xml

```xml
 		<EditText
			...
			fromSignUp="@{signInViewModel.fromSignUp.id}"
            ifActive="@{signInViewModel.id}"
			... />

  		    ...

        <EditText
            ...
            fromSignUp="@{signInViewModel.fromSignUp.password}"
            ifActive="@{signInViewModel.password}"
            ... />
```



## 9. BindingAdapter for MainActivity

```kotlin
object MainBinding {
    @BindingAdapter("mainAdapter")
    @JvmStatic
    fun mainAdapter(viewPager : ViewPager, fragmentManager : FragmentManager) {
        viewPager.adapter = MainPagerAdapter(fragmentManager)
    }

    @BindingAdapter("bottomListener")
    @JvmStatic
    fun bottomListener(bottomNavigationView : BottomNavigationView, viewPager : ViewPager) {
        bottomNavigationView.setBottomNavigationListener(viewPager)
    }

    @BindingAdapter("viewPagerListener")
    @JvmStatic
    fun viewPagerListener(viewPager : ViewPager, bottomNavigationView : BottomNavigationView) {
        viewPager.addMainPagerListener(bottomNavigationView)
    }
}
```

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply{
            fragmentManager = supportFragmentManager
            viewPager = binding.vpMain
            bottomNavigationView = binding.bottomNavigation
            lifecycleOwner = this@MainActivity
        }
    }
}
```



## 9-2. activity_main.xml

```xml
...
		<androidx.viewpager.widget.ViewPager
            ...
            mainAdapter="@{fragmentManager}"
            viewPagerListener="@{bottomNavigationView}"
            ... />

        <com.google.android.material.bottomnavigation.BottomNavigationView
            ...
            bottomListener="@{viewPager}"
            ... />
...
```



## 10. BindingAdapter for RecyclerFragment

```kotlin
object RecyclerBinding {
    @BindingAdapter("setLayout")
    @JvmStatic
    fun setLayout(recyclerView : RecyclerView, layoutItem : Int) {
        if(layoutItem == R.layout.item_recycler_linear)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        else recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 3)
    }
}
```



## 10-1. RecyclerViewModel

```kotlin
...

private val _layoutItem = MutableLiveData<Int>(R.layout.item_recycler_linear)
val layoutItem : LiveData<Int>
    get() = _layoutItem
...

fun changeLayout(){
    if(_layoutItem.value==R.layout.item_recycler_linear) _layoutItem.value = R.layout.item_recycler_grid
    else _layoutItem.value = R.layout.item_recycler_linear
}
```



## 10-2. fragment_recycler.xml

```xml
        ...
		<androidx.recyclerview.widget.RecyclerView
            ...
			setLayout="@{recyclerViewModel.layoutItem}"
            ... />
```



## 11. BindingAdapter for HomeFragment

```kotlin
object HomeBinding {
    @BindingAdapter("setMyName")
    @JvmStatic
    fun setMyName(tvName : TextView, myName : String?) {
        myName?.let{ tvName.text = myName }
    }

    @BindingAdapter("homeAdapter")
    @JvmStatic
    fun homeAdapter(viewPager : ViewPager, fragmentManager : FragmentManager) {
        viewPager.adapter = HomePagerAdapter(fragmentManager)
    }

    @BindingAdapter("setTabListener")
    @JvmStatic
    fun setTabListener(tabLayout : TabLayout, viewPager : ViewPager) {
        tabLayout.addTabLayoutListener(viewPager)
    }

    @BindingAdapter("setUpWithViewPager")
    @JvmStatic
    fun setUpWithViewPager(viewPager : ViewPager, tabLayout : TabLayout) {
        viewPager.addHomePagerListener(tabLayout)
    }

    @BindingAdapter("setTabItems")
    @JvmStatic
    fun setTabItems(tabLayout : TabLayout, tabItems : String) {
        tabLayout.addTab(tabLayout.newTab().setText(tabItems.split(" ")[0])) ;
        tabLayout.addTab(tabLayout.newTab().setText(tabItems.split(" ")[1])) ;
    }
}
```

```kotlin
...
override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.homeViewModel = homeViewModel
        binding.fragmentManager = childFragmentManager
        binding.viewPager = binding.vpHome
        binding.bindingTabLayout = binding.tabLayout
        binding.lifecycleOwner = this@HomeFragment
        return binding.root
	}
...
```



## 11-1. HomeViewModel

```kotlin
...

private val _myName = MutableLiveData<String>()
val myName : LiveData<String>
    get() = _myName

private val _tabItems = MutableLiveData<String>("INFO OTHER")
val tabItems : LiveData<String>
    get() = _tabItems

...

fun getMyName() = viewModelScope.launch(Dispatchers.IO) {
    _myName.postValue(userDao.getMyName(sharedPref.getString("id", "")!!))
}
```



## 11-2. fragment_home.xml

```xml
...
		<androidx.constraintlayout.widget.ConstraintLayout>          
			...
			<TextView
                ...
				setMyName="@{homeViewModel.myName}"
                ... />
        </androidx.constraintlayout.widget.ConstraintLayout>
		
		<androidx.viewpager.widget.ViewPager
        	...
            setUpWithViewPager="@{bindingTabLayout}"
            homeAdapter="@{fragmentManager}"
            ... />

        <com.google.android.material.tabs.TabLayout
            ...
            setTabListener="@{viewPager}"
            setTabItems="@{homeViewModel.tabItems}"
            ... />
...
```



## 12. MyPageFragment

```kotlin
...

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myPageViewModel.getMy()
    }

override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    val binding : FragmentMyPageBinding = 
    DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false)
    binding.myPageViewModel = myPageViewModel
    binding.my = myPageViewModel.my
    return binding.root
}

override fun onStart(){
    super.onStart()

    myPageViewModel.logout.observe(this, Observer{ logout ->
        logout.let { if(it) requireActivity().startActivity<SignInActivity>() }
    })
}
```



## 12-1. fragment_my_page.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>
        <variable
            name="my"
            type="com.kimym.onsopt.room.User"/>
        <variable
            name="myPageViewModel"
            type="com.kimym.onsopt.ui.mypage.MyPageViewModel"/>
    </data>

		...

        <TextView
            ...
			android:text="@{my.id}"
			... />

        <TextView
            ...
			android:text="@{my.name}"
			... />

        <TextView
            ...
			android:text="@{my.part}"
			... />

        <TextView
            ...
			android:text="@{my.date}"
			... />

        <com.google.android.material.floatingactionbutton.FloatingActionButton
            android:id="@+id/fab_logout"
            ...
			android:onClick="@{() -> myPageViewModel.logout()}"
            ... />
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```



## 13. addViewPagerListener

```kotlin
fun ViewPager.addMainPagerListener(bottomNavigationView : BottomNavigationView){
    this.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {}

        override fun onPageSelected(position: Int) { bottomNavigationView.menu.getItem(position).isChecked = true }
    })
}
```

```kotlin
fun ViewPager.addHomePagerListener(tabLayout : TabLayout){
    this.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {}

        override fun onPageSelected(position: Int) { tabLayout.getTabAt(position)!!.select() }
    })
}
```



## 14. addTabLayoutListener

```kotlin
fun TabLayout.addTabLayoutListener(viewPager : ViewPager){
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {}

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabSelected(tab: TabLayout.Tab?) { viewPager.currentItem = tab!!.position }
    })
}
```



## 15. setBottomNavigationListener

```kotlin
fun BottomNavigationView.setBottomNavigationListener(viewPager : ViewPager) {
    this.setOnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.nv_home -> viewPager.currentItem = 0
            R.id.nv_user -> viewPager.currentItem = 1
            R.id.nv_myPage -> viewPager.currentItem = 2
        }
        true
    }
}
```