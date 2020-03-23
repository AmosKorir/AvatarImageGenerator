package com.avatarfirst.avatargenlib

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.text.TextPaint

/**
 * Created by Korir on 1/21/20.
 */
class AvatarGenerator {
  companion object {
    lateinit var uiContext: Context
    var texSize = 0F

    fun avatarImage(context: Context, size: Int, shape: Int, name: String): BitmapDrawable {
      return  avatarImageGenerate(context ,size ,shape, name,AvatarConstants.COLOR700)
    }

    fun avatarImage(context: Context, size: Int, shape: Int, name: String,colorModel:Int): BitmapDrawable {
      return  avatarImageGenerate(context ,size ,shape, name,colorModel)
    }

    fun avatarImageGenerate(context: Context, size: Int, shape: Int, name: String,colorModel:Int): BitmapDrawable {
      uiContext = context
      val width = size
      val hieght = size

      texSize = calTextSize(size)
      val label = firstCharacter(name)
      val textPaint = textPainter()
      val painter = painter()
      val areaRect = Rect(0, 0, width, width)

      if (shape == 0) {
        painter.color = RandomColors().getColor()
      } else {
        painter.color = Color.TRANSPARENT
      }

      val bitmap = Bitmap.createBitmap(width, width, ARGB_8888)
      val canvas = Canvas(bitmap)
      canvas.drawRect(areaRect, painter)

      //reset painter
      if (shape == 0) {
        painter.color = Color.TRANSPARENT
      } else {
        painter.color = RandomColors().getColor()
      }

      val bounds = RectF(areaRect)
      bounds.right = textPaint.measureText(label, 0, 1)
      bounds.bottom = textPaint.descent() - textPaint.ascent()

      bounds.left += (areaRect.width() - bounds.right) / 2.0f
      bounds.top += (areaRect.height() - bounds.bottom) / 2.0f

      canvas.drawCircle(width.toFloat() / 2, hieght.toFloat() / 2, width.toFloat() / 2, painter)
      canvas.drawText(label, bounds.left, bounds.top - textPaint.ascent(), textPaint)
      return BitmapDrawable(uiContext.resources, bitmap)

    }

    private fun firstCharacter(name: String): String {
      return name.first().toString().toUpperCase()
    }

    private fun textPainter(): TextPaint {
      val textPaint = TextPaint()
      textPaint.textSize = texSize * uiContext.resources.displayMetrics.scaledDensity
      textPaint.color = Color.WHITE
      return textPaint
    }

    private fun painter(): Paint {
      return Paint()
    }

    private fun calTextSize(size: Int): Float {
      return (size / 3.125).toFloat()
    }
  }
}