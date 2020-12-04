

#  sixth branch



<div style="text-align : center">
    <img src="https://user-images.githubusercontent.com/63637706/101171289-e4f76900-3682-11eb-8105-5a99ba00aade.gif" width="350" height="600">
    <img src="https://user-images.githubusercontent.com/63637706/101171309-e9238680-3682-11eb-865d-1258d78d7779.gif" width="350" height="600">
</div>


# 1. Postman

<div style="text-align : center">
    <img src="https://user-images.githubusercontent.com/63637706/101178611-9222af00-368c-11eb-89d6-48e9439dc7b5.png">
    <img src="https://user-images.githubusercontent.com/63637706/101178360-41ab5180-368c-11eb-8a88-b30bf94b795a.png">
</div>






## 2. Package

<div>
    <img src="https://user-images.githubusercontent.com/63637706/101171010-8d58fd80-3682-11eb-875e-1a60edeb9feb.PNG" width="200">
    <img src="https://user-images.githubusercontent.com/63637706/101171014-8e8a2a80-3682-11eb-946f-6f20d6f158cf.PNG" width="200">
    <img src="https://user-images.githubusercontent.com/63637706/101171017-8e8a2a80-3682-11eb-9fd7-2491aae9d410.PNG" width="200">
    <img src="https://user-images.githubusercontent.com/63637706/101171018-8f22c100-3682-11eb-8e18-2d91d50093b5.PNG" width="200">
</div>




## 3. Dependencies

<img src="https://user-images.githubusercontent.com/63637706/101171546-415a8880-3683-11eb-8c44-3567a466682f.PNG">



## 4. Theme

<img src="https://user-images.githubusercontent.com/63637706/97772630-3c8a4c80-1b8c-11eb-89e9-86ae135e1a89.PNG">  



## 5. Structure

<div style="text-align : center">
    <img src="https://user-images.githubusercontent.com/63637706/101176105-62be7300-3689-11eb-886d-4a61f6a31638.png">
    <img src="https://user-images.githubusercontent.com/63637706/101176102-6225dc80-3689-11eb-9b7c-22485e5a01af.png">
    <img src="https://user-images.githubusercontent.com/63637706/101176104-62be7300-3689-11eb-9912-88307540272b.png">
    <img src="https://user-images.githubusercontent.com/63637706/101176094-5fc38280-3689-11eb-94ca-d18b45517ae4.PNG">
    <img src="https://user-images.githubusercontent.com/63637706/101176097-60f4af80-3689-11eb-8d5a-f71846c96ac8.png">
    <img src="https://user-images.githubusercontent.com/63637706/101176099-60f4af80-3689-11eb-8a72-c70f47c9a827.png">
    <img src="https://user-images.githubusercontent.com/63637706/101176096-605c1900-3689-11eb-9035-a6f4971ccf45.png">
    <img src="https://user-images.githubusercontent.com/63637706/101176100-618d4600-3689-11eb-845d-265d41060bb4.png">
</div>





## 6. Flow

 * main : MainActivity = { HomeFragment, DummyFragment, SearchFragment }
 * home : HomeFragment = { InfoFragment, OtherFragment }
 * signIn : SignInActivity -> HomeFragment
 * vpMain : HomeFragment <-> DummyFragment <-> SearchFragment
 * vpHome : InfoFragment <-> OtherFragment
 * tv_logout : HomeFragment -> SignInActivity



## 7. RetrofitBuilder

```kotlin
object RetrofitBuilder {
    private var userRetrofit : Retrofit = Retrofit.Builder().baseUrl("http://**.***.**.***:****")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var userService : UserRequestInterface = userRetrofit.create(
        UserRequestInterface::class.java)

    private var dummyRetrofit : Retrofit = Retrofit.Builder().baseUrl("https://reqres.in/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var dummyService : DummyRequestInterface = dummyRetrofit.create(DummyRequestInterface::class.java)

    private var searchRetrofit : Retrofit = Retrofit.Builder().baseUrl("https://dapi.kakao.com/v2/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var searchService : SearchRequestInterface = searchRetrofit.create(SearchRequestInterface::class.java)
}
```



## 8-1. UserRequestInterface

```kotlin
interface UserRequestInterface {
    @Headers("Content-Type:application/json")
    @POST("/users/signin")
    suspend fun signIn(
        @Body body : RequestSignIn
    ) : ResponseUser

    @Headers("Content-Type:application/json")
    @POST("/users/signup")
    suspend fun signUp(
        @Body body : RequestSignUp
    ) : ResponseUser
}
```



## 8-2. DummyRequestInterface

```kotlin
interface DummyRequestInterface {
    @GET("users")
    suspend fun getDummyUsers(
        @Query("page") page : Int
    ) : DummyUsers
}
```



## 8-3. SearchRequestInterface

```kotlin
interface SearchRequestInterface {
    @Headers("Authorization: KakaoAK {REST-API-KEY}")
    @GET("web")
    suspend fun getWebSearch(
        @Query("query") query : String,
        @Query("page") page : Int
    ) : WebData
}
```



## 9-1. UserRepository

```kotlin
class UserRepository @Inject constructor(
    private val userDataSource : UserDataSource,
    private val sharedPreferences : SharedPreferences
) {
    suspend fun requestSignIn(body : RequestSignIn) = userDataSource.signIn(body)

    suspend fun requestSignUp(body : RequestSignUp) = userDataSource.signUp(body)

    fun getEmail() = sharedPreferences.getString("email", "")

    fun getPassword() = sharedPreferences.getString("password", "")

    fun putEmail(id : String) {
        sharedPreferences.edit().putString("email", id).apply()
    }

    fun putPassword(password : String) {
        sharedPreferences.edit().putString("password", password).apply()
    }

    fun logout() {
        sharedPreferences.edit().clear().apply()
    }
}
```



## 9-2. DummyRepository

```kotlin
class DummyRepository @Inject constructor(
    private val dummyDataSource : DummyDataSource
) {
    suspend fun getDummyUsers(page : Int) = dummyDataSource.getDummyUsers(page)
}
```



## 9-3. SearchRepository

```kotlin
class SearchRepository @Inject constructor(
    private val searchDataSource : SearchDataSource
) {
    suspend fun getWebSearch(query : String, page : Int) = searchDataSource.getWebSearch(query, page)
}
```



## 10-1. DataModule

```kotlin
@InstallIn(ApplicationComponent::class)
@Module
object DataModule {
    
    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences("pref", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideUserRequestInterface(): UserRequestInterface = RetrofitBuilder.userService

    @Provides
    @Singleton
    fun provideUserRepository(userDataSourceImpl : UserDataSource, sharedPreferences : SharedPreferences) =
        UserRepository(userDataSourceImpl, sharedPreferences)

    @Provides
    @Singleton
    fun provideDummyRequestInterface(): DummyRequestInterface = RetrofitBuilder.dummyService

    @Provides
    @Singleton
    fun provideDummyRepository(dummyDataSourceImpl : DummyDataSource) =
        DummyRepository(dummyDataSourceImpl)

    @Provides
    @Singleton
    fun provideSearchRequestInterface(): SearchRequestInterface = RetrofitBuilder.searchService

    @Provides
    @Singleton
    fun provideSearchRepository(searchDataSourceImpl : SearchDataSource) =
        SearchRepository(searchDataSourceImpl)
}
```



## 10-2. DataModuleBinds

```kotlin
@InstallIn(ApplicationComponent::class)
@Module
abstract class DataModuleBinds {

    @Binds
    abstract fun bindUserDataSource(userDataSourceImpl : UserDataSourceImpl) : UserDataSource

    @Binds
    abstract fun bindDummyDataSource(dummyDataSourceImpl : DummyDataSourceImpl) : DummyDataSource

    @Binds
    abstract fun bindSearchDataSource(searchDataSourceImpl : SearchDataSourceImpl) : SearchDataSource
}
```



## 10-3. OnSoptApplication

```kotlin
@HiltAndroidApp
class OnSoptApplication : Application() {
}
```



## 11. DummyBinding

```kotlin
object DummyBinding {
    @BindingAdapter("setImage")
    @JvmStatic
    fun setImage(imageView : ImageView, url : String) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.ic_loading_glide)
            .error(R.drawable.ic_error_glide)
            .into(imageView)
    }

    @BindingAdapter("setLayout")
    @JvmStatic
    fun setLayout(recyclerView : RecyclerView, layoutItem : Int) {
        if(layoutItem == R.layout.item_dummy_linear) recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        else recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 3)
    }

    @BindingAdapter("setUsers")
    @JvmStatic
    fun setUsers(recyclerView : RecyclerView, userList : List<DummyUserInfo>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as DummyAdapter<*>) { userList?.let{setUsers(it)}}
    }
}
```



## 11-1. getDummyUsers() in DummyViewModel

```kotlin
fun getDummyUsers() = viewModelScope.launch(Dispatchers.IO) {
    if(_page.value==1) _page.postValue(2) else _page.postValue(1)
    _allUsers.postValue(dummyRepository.getDummyUsers(page.value!!).data)
}
```



## 12. SearchBinding

```kotlin
object SearchBinding {
    @BindingAdapter("visibleResetButton")
    @JvmStatic
    fun visibleResetButton(view : View, keyword : String) {
        view.visibility = if (keyword.isEmpty()) View.INVISIBLE else View.VISIBLE
    }

    @BindingAdapter("setListItem")
    @JvmStatic
    fun setListItem(recyclerView : RecyclerView, webs : List<Document>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as SearchAdapter) { webs?.let{ setResults(it) } }
    }

    @BindingAdapter("setDateText")
    @JvmStatic
    fun setDateText(textView : TextView, dateString : String) {
        textView.text = dateString.split("T")[0]
    }

    @BindingAdapter("setTitleText")
    @JvmStatic
    fun setTitleText(textView : TextView, titleString : String) {
        textView.text = Html.fromHtml(titleString, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    @BindingAdapter("setPageText")
    @JvmStatic
    fun setPageText(textView : TextView, page : Int) {
        textView.text = String.format(textView.context.resources.getString(R.string.page), page-1)
    }
}
```



## 12-1. getWebSearch() in SearchViewModel

```kotlin
fun getWebSearch() = viewModelScope.launch(Dispatchers.IO){
    _allWeb.postValue(repository.getWebSearch(keyword.value!!, page.value!!))
    if(page.value!!<50) _page.postValue(_page.value!!+1)
}
```