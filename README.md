# first branch



## 1. Package

<img src="https://user-images.githubusercontent.com/63637706/96594778-f3cdca80-1325-11eb-86d7-40cf27adde78.PNG">  



## 2. Dependencies

<img src="https://user-images.githubusercontent.com/63637706/96594875-0f38d580-1326-11eb-8e9e-d81b7748969e.PNG">  



## 3. Theme

<img src="https://user-images.githubusercontent.com/63637706/96597383-bdde1580-1328-11eb-82a4-000577758947.PNG">  



## 4. Structure

<div style="text-align : center">
    <img src="https://user-images.githubusercontent.com/63637706/96602116-a35a6b00-132d-11eb-9815-51a60134f85d.PNG">
    <img src="https://user-images.githubusercontent.com/63637706/96602250-c7b64780-132d-11eb-8d71-0dd8cfc8fb49.PNG">
    <img src="https://user-images.githubusercontent.com/63637706/96605131-cdf9f300-1330-11eb-8f71-1c0fef71abf6.png">
</div>





## 5. Flow

 * login & autoLogin : SignInActivity -> HomeActivity
 * register : SignInActivity -> SignUpActivity
 * register success : SignUpActivity -> SignInActivity
 * logout : HomeActivity -> SignInActivity



## 6. SignInViewModel

```kotlin
 private val _autoLogin = MutableLiveData<Boolean>(false)
    val autoLogin : LiveData<Boolean>
        get() = _autoLogin

    private val _isValid = MutableLiveData<Boolean>(false)
    val isValid : LiveData<Boolean>
        get() = _isValid
```

```kotlin
 fun autoLogin(){
        if(id.value == sharedPref.getString("id", "") && id.value != ""
            && password.value == sharedPref.getString("password", "") && password.value != "")
            _autoLogin.value = true
    }
```

```kotlin
 fun validation(){
        if(!id.value.isNullOrEmpty() && !password.value.isNullOrEmpty()) _isValid.value = true
    }
```



## 7. SignUpViewModel

```kotlin
private val _isSamePassword = MutableLiveData<Boolean>(false)
    val isSamePassword : LiveData<Boolean>
        get() = _isSamePassword

    private val _isValid = MutableLiveData<Boolean>(false)
    val isValid : LiveData<Boolean>
        get() = _isValid
```

```kotlin
  fun checkPassword(password : String, passwordCheck : String){
        _isSamePassword.value = password == passwordCheck
  }
```

```kotlin
  fun validation() {
        _isValid.value = false
        if(!name.value.isNullOrEmpty() && !id.value.isNullOrEmpty() && _isSamePassword.value!!)
            _isValid.value = true
  }
```



## 8. SignUpActivity (for password checking)

```kotlin
   signUpViewModel.isSamePassword.observe(this, Observer{ value->
        value.let {img_check_pw.visibility = if(value) View.VISIBLE else View.INVISIBLE }
   })
```



## 9. HomeActivity (logout)

```kotlin
   btn_logout.setOnClickListener{
      sharedEdit.clear()
      sharedEdit.apply()
      startActivity<SignInActivity>()
      finish()
   }
```