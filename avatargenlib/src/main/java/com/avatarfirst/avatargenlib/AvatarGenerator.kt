package com.avatarfirst.avatargenlib

import android.content.Context
import android.graphics.*
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.drawable.BitmapDrawable
import android.text.TextPaint
import java.util.*

/**
 * Created by Korir on 1/21/20.
 */
class AvatarGenerator(private val builder: AvatarBuilder) {

    class AvatarBuilder(private val context: Context) {

        private var textSize = 100
        private var size = 14
        private var borderWidth = 10
        private var name = " "
        private var backgroundColor: Int? = null
        private var borderColor: Int? = null
        private var isWithBorder = false
        private var shapeType = AvatarConstants.CIRCLE
        private var isUpperCase = false

        //internal configs
        private var isWithGradient =false


        fun setTextSize(textSize: Int) = apply {
            this.textSize = textSize
        }

        fun setAvatarSize(int: Int) = apply {
            this.size = int
        }

        fun setLabel(label: String) = apply {
            this.name = label
        }

        fun setBackgroundColor(color: Int) = apply {
            this.backgroundColor = color
        }

        fun setBorderColor(color: Int) = apply {
            this.borderColor = color
        }

        fun setBorderWidth(width: Int) = apply {
            this.borderWidth = width
        }

        fun toUpperCase() = apply {
            isUpperCase = true
        }

        fun toLowerCase() = apply {
            isUpperCase = false
        }

        fun setWithBorder(borderEnabled: Boolean) = apply {
            isWithBorder = borderEnabled
        }

        fun toSquare() = apply {
            this.shapeType = AvatarConstants.RECTANGLE
        }

        fun toCircle() = apply {
            this.shapeType = AvatarConstants.CIRCLE
        }


        fun build(): BitmapDrawable {
            return avatarImageGenerate(
                context,
                size,
                shapeType,
                name,
                textSize,
                borderWidth,
                AvatarConstants.COLOR700
            )
        }


        private fun avatarImageGenerate(
            context: Context,
            size: Int,
            shape: Int,
            name: String,
            textSize: Int,
            borderWidth: Int,
            colorModel: Int
        ): BitmapDrawable {

            uiContext = context

            texSize = calTextSize(textSize)
            val label = firstCharacter(name)
            val textPaint = textPainter()
            var painter = painter()
            painter.isAntiAlias = true

            val areaRect = Rect(0, 0, size, size)

            if (shape == AvatarConstants.RECTANGLE) {
                painter.color = Color.TRANSPARENT
            } else {
                if (isWithGradient){
                    painter=drawBackgroundGredient(painter,size)
                }else{
                    painter.color = backgroundColor ?: RandomColors(colorModel).getColor()

                }
            }

            val bitmap = Bitmap.createBitmap(size, size, ARGB_8888)
            val canvas = Canvas(bitmap)
            canvas.drawRect(areaRect, painter)

            val bounds = RectF(areaRect)
            bounds.right = textPaint.measureText(label, 0, 1)
            bounds.bottom = textPaint.descent() - textPaint.ascent()

            bounds.left += (areaRect.width() - bounds.right) / 2.0f
            bounds.top += (areaRect.height() - bounds.bottom) / 2.0f

            canvas.drawCircle(size.toFloat() / 2, size.toFloat() / 2, size.toFloat() / 2, painter)

            if (isWithBorder) {
                when (shape) {
                    AvatarConstants.RECTANGLE -> {
                        drawBorder(canvas, areaRect, painter)

                    }
                    AvatarConstants.CIRCLE -> {
                        drawCircleBorder(canvas, size, borderWidth, painter)
                    }
                }
            }

            canvas.drawText(label, bounds.left, bounds.top - textPaint.ascent(), textPaint)

            return BitmapDrawable(uiContext.resources, bitmap)

        }

        private fun drawCircleBorder(
            canvas: Canvas,
            originalSize: Int,
            borderWidth: Int,
            painter: Paint
        ) {
            val size = originalSize - borderWidth
            painter.color = borderColor ?: Color.BLACK
            painter.style = Paint.Style.STROKE
            painter.strokeWidth = borderWidth.toFloat()
            canvas.drawCircle(
                originalSize.toFloat() / 2,
                originalSize.toFloat() / 2,
                size.toFloat() / 2,
                painter
            )
        }

        private fun drawBackgroundGredient(painter: Paint, size: Int): Paint {
            painter.shader = LinearGradient(
                0f,
                0f,
                0f,
                size.toFloat(),
                Color.BLACK,
                Color.WHITE,
                Shader.TileMode.MIRROR
            )
            return painter
        }

        private fun drawBorder(canvas: Canvas, shape: Rect, painter: Paint) {
            painter.color = borderColor ?: Color.BLACK
            painter.style = Paint.Style.STROKE
            painter.strokeWidth = borderWidth.toFloat()
            canvas.drawRect(shape, painter)
        }

        private fun firstCharacter(name: String): String {
            if (name.isEmpty()) {
                return "-"
            }
            return if (isUpperCase) {
                name.first().toString().toUpperCase(Locale.ROOT)
            } else {
                name.first().toString().toLowerCase(Locale.ROOT)
            }
        }

        private fun textPainter(): TextPaint {
            val textPaint = TextPaint()
            textPaint.isAntiAlias = true
            textPaint.textSize = texSize * uiContext.resources.displayMetrics.scaledDensity
            textPaint.color = Color.WHITE
            return textPaint
        }

        private fun painter(): Paint {
            return Paint()
        }

        private fun calTextSize(size: Int): Float {
            return (size).toFloat()
        }

    }


    /**
     * Deprecate and will be removed
     */
    companion object {
        private lateinit var uiContext: Context
        private var texSize = 0F

        @Deprecated("Switch to using builder method")
        fun avatarImage(context: Context, size: Int, shape: Int, name: String): BitmapDrawable {
            return avatarImageGenerate(context, size, shape, name, AvatarConstants.COLOR700)
        }


        fun avatarImage(
            context: Context,
            size: Int,
            shape: Int,
            name: String,
            colorModel: Int
        ): BitmapDrawable {
            return avatarImageGenerate(context, size, shape, name, colorModel)
        }

        private fun avatarImageGenerate(
            context: Context,
            size: Int,
            shape: Int,
            name: String,
            colorModel: Int
        ): BitmapDrawable {
            uiContext = context

            texSize = calTextSize(size)
            val label = firstCharacter(name)
            val textPaint = textPainter()
            val painter = painter()
            painter.isAntiAlias = true
            val areaRect = Rect(0, 0, size, size)

            if (shape == 0) {
                painter.color = RandomColors(colorModel).getColor()
            } else {
                painter.color = Color.TRANSPARENT
            }

            val bitmap = Bitmap.createBitmap(size, size, ARGB_8888)
            val canvas = Canvas(bitmap)
            canvas.drawRect(areaRect, painter)

            //reset painter
            if (shape == 0) {
                painter.color = Color.TRANSPARENT
            } else {
                painter.color = RandomColors(colorModel).getColor()
            }

            val bounds = RectF(areaRect)
            bounds.right = textPaint.measureText(label, 0, 1)
            bounds.bottom = textPaint.descent() - textPaint.ascent()

            bounds.left += (areaRect.width() - bounds.right) / 2.0f
            bounds.top += (areaRect.height() - bounds.bottom) / 2.0f

            canvas.drawCircle(size.toFloat() / 2, size.toFloat() / 2, size.toFloat() / 2, painter)
            canvas.drawText(label, bounds.left, bounds.top - textPaint.ascent(), textPaint)
            return BitmapDrawable(uiContext.resources, bitmap)

        }


        private fun firstCharacter(name: String): String {
            return name.first().toString().toUpperCase(Locale.ROOT)
        }

        private fun textPainter(): TextPaint {
            val textPaint = TextPaint()
            textPaint.isAntiAlias = true
            textPaint.textSize = texSize * uiContext.resources.displayMetrics.scaledDensity
            textPaint.color = Color.WHITE
            return textPaint
        }

        private fun painter(): Paint {
            return Paint()
        }

        private fun calTextSize(size: Int): Float {
            return (size).toFloat()
        }
    }
}