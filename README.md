

# fourth branch



<div style="text-align : center">
    <img src="https://user-images.githubusercontent.com/63637706/99883908-99e65a80-2c6d-11eb-85a4-e6fe5975bb1e.gif" width="300" height="600">
    <img src="https://user-images.githubusercontent.com/63637706/99883911-9d79e180-2c6d-11eb-9d0e-2d06067e78d8.gif" width="300" height="600">
</div>






## 1. font

<img src="https://user-images.githubusercontent.com/63637706/99883975-03feff80-2c6e-11eb-82fd-7e7954c0828d.PNG">  


```xml
<?xml version="1.0" encoding="utf-8"?>
<font-family
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <font
        android:font="@font/cairo_extra_light"
        android:fontWeight="200"
        android:fontStyle="normal"
        app:font="@font/cairo_extra_light"
        tools:targetApi="o"/>

    <font
        android:font="@font/cairo_light"
        android:fontWeight="300"
        android:fontStyle="normal"
        app:font="@font/cairo_light"
        tools:targetApi="o"/>

    <font
        android:font="@font/cairo_regular"
        android:fontWeight="400"
        android:fontStyle="normal"
        app:font="@font/cairo_regular"
        tools:targetApi="o"/>

    <font
        android:font="@font/cairo_semi_bold"
        android:fontWeight="600"
        android:fontStyle="normal"
        app:font="@font/cairo_semi_bold"
        tools:targetApi="o"/>

    <font
        android:font="@font/cairo_bold"
        android:fontWeight="700"
        android:fontStyle="normal"
        app:font="@font/cairo_bold"
        tools:targetApi="o"/>

    <font
        android:font="@font/cairo_black"
        android:fontWeight="900"
        android:fontStyle="normal"
        app:font="@font/cairo_black"
        tools:targetApi="o"/>

</font-family>
```





## 2. Structure

<div style="text-align : center">
    <img src="https://user-images.githubusercontent.com/63637706/99884226-ee8ad500-2c6f-11eb-84f7-e7823b2974a2.png">
    <img src="https://user-images.githubusercontent.com/63637706/99884227-ef236b80-2c6f-11eb-8583-215ead9d409e.png">
    <img src="https://user-images.githubusercontent.com/63637706/99884228-efbc0200-2c6f-11eb-8fca-f94b00b670d3.png">
    <img src="https://user-images.githubusercontent.com/63637706/99884229-f0549880-2c6f-11eb-938c-b22b5b718afa.png">
    <img src="https://user-images.githubusercontent.com/63637706/99884230-f0549880-2c6f-11eb-9148-e815cd0ab0e6.png">
</div>



## 3-1. Animation in SignInActivity

```kotlin
binding.imgLogo.animate().apply{
    duration = 2000
    rotation(360f)
}.start()
```



## 3-2. Animation in InfoFragment & OtherFragment

```kotlin
tv_info.scaleX = 0f
tv_info.scaleY = 0f
tv_info.animate().apply {
    duration=2000
    scaleX(1f)
    scaleY(1f)
}.start()
```

```kotlin
tv_other.scaleX = 0f
tv_other.scaleY = 0f
tv_other.animate().apply {
    duration=2000
    scaleX(1f)
    scaleY(1f)
}.start()
```



## 4. BindingAdapter for RecyclerFragment

```kotlin
@BindingAdapter("setList")
@JvmStatic
fun setList(recyclerView : RecyclerView, userList : List<User>?) {
    if (recyclerView.adapter != null) with(recyclerView.adapter as RecyclerAdapter<*>) { 
        userList?.let { setUsers(it) } }
	}
```
