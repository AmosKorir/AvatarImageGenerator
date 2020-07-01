# AvatarImageGenerator

 [![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=shield)](https://app.circleci.com/pipelines/github/AmosKorir/AvatarImageGenerator/) [ ![Download](https://api.bintray.com/packages/skyways/AvatarGenerator/AvatarGenerator/images/download.svg?version=1.4) ](https://bintray.com/skyways/AvatarGenerator/AvatarGenerator/1.4/link)
Generate first letter avatar Image like gmail's contact avatar. It generates an drawable that can be be set to an ImageView.

<img  width="200" height="400" src="https://github.com/skyways/AvatarImageGenerator/blob/master/art/Screen2.png"/>

<img  width="200" height="400" src="https://github.com/skyways/AvatarImageGenerator/blob/master/art/screen.jpeg"/>



**Installation**

Add the Following to your gradle file.

```java
         implementation 'com.first.avatargenerator:AvatarImageGenerator:VERSION'
```

**Supports**

Using glide or Picasso you can set the drawable to an imageView as a placeholder.

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

**Color Model**
The current version supports material colors A 400,700 and 900.

1. COLOR400
2. COLOR700
3. COLOR900

```java
 Picasso.get()
    .load("https://brokenfortest")
    .resize(50, 50)
    .placeholder(AvatarGenerator.avatarImage(this, 200, AvatarConstants.CIRCLE, "Android",AvatarConstants.COLOR900))
    .into(imageView2)
```

**Shape**

1. Circle
2. Square

```java
AvatarConstants.RECTANGLE,
AvatarConstants.CIRCLE

```
