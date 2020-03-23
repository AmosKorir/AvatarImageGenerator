package com.avatarfirst.avatagen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.avatarfirst.avatargenlib.AvatarConstants
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.imageView
import kotlinx.android.synthetic.main.activity_main.imageView2
import kotlinx.android.synthetic.main.activity_main.imageView3

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    imageView.setImageDrawable(
      AvatarGenerator.avatarImage(
        this,
        200,
        AvatarConstants.RECTANGLE,
        "Skyways"
      )
    )
//picaso
    Picasso.get()
      .load("https://brokenfortest")
      .resize(50, 50)
      .placeholder(AvatarGenerator.avatarImage(this, 200, AvatarConstants.CIRCLE, "Android",AvatarConstants.COLOR900))
      .into(imageView2)

    //Glide
    Glide.with(this)
      .load("http://brokenfortest")
      .placeholder(AvatarGenerator.avatarImage(this, 200, AvatarConstants.CIRCLE, "Kotjav"))
      .into(imageView3)

  }

}
