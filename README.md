# AvatarImageGenerator
<img src="https://circleci.com/gh/skyways/AvatarImageGenerator.svg?style=svg"/> <a href="https://bintray.com/skyways/AvatarGenerator/AvatarGenerator/1.2/link"><img src="https://api.bintray.com/packages/skyways/AvatarGenerator/AvatarGenerator/images/download.svg?version=1.2"/></a>

Generate first letter avatar Image
    
   
<img  width="200" height="400" src="https://github.com/skyways/AvatarImageGenerator/blob/master/art/screen.jpeg"/>

**Installation**

Add the Following to your gradle file.

```java
         implementation 'com.first.avatargenerator:AvatarImageGenerator:1.2'
 ```

**Supports**


  1. Picasso
  2. Glide
  
  **Picasso**
  
  ```java
   Picasso.get()
      .load("https://brokenfortest")
      .resize(50, 50)
      .placeholder(AvatarGenerator.avatarImage(this, 200, AvatarConstants.CIRCLE, "Android"))
      .into(imageView2)
  ```
  
   **Glide**
  
  ```java
   Glide.with(this)
      .load("http://brokenfortest")
      .placeholder(AvatarGenerator.avatarImage(this, 200, AvatarConstants.CIRCLE, "Kotjav"))
      .into(imageView3)
  ```
  **Without any Library**
  
  
  ```java
  imageView.setImageDrawable(
      AvatarGenerator.avatarImage(
        this,
        200,
        AvatarConstants.RECTANGLE,
        "Skyways"
      )
  ```
  
  
  
  **Shape**
  
  1. Circle
  2. Square
  
  ```java
  AvatarConstants.RECTANGLE,
  AvatarConstants.CIRCLE
  
  ```
  
  
