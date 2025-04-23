package com.example.android.timetabledemo.timetabledemo // Ensure package matches the folder structure

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.android.timetabledemo.R
import java.util.Random
import kotlin.math.min

class LetterImageView @JvmOverloads constructor( // Use @JvmOverloads for multiple constructors
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var mLetter = 0.toChar()
    private var mTextPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = mTextColor
    }
    private var mBackgroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = randomColor()
    }
    private var mTextColor = Color.WHITE

    private var isOval: Boolean = false // Made private

    var letter: Char
        get() = mLetter
        set(letter) {
            mLetter = letter
            invalidate()
        }

    var textColor: Int
        get() = mTextColor
        set(textColor) {
            mTextColor = textColor
            mTextPaint.color = textColor
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (drawable == null) {
            // Set a text font size based on the height of the view
            mTextPaint.textSize = canvas.height - textPadding * 2
            if (isOval) {
                canvas.drawCircle(
                    canvas.width / 2f, canvas.height / 2f,
                    (min(canvas.width.toDouble(), canvas.height.toDouble()) / 2f).toFloat(),
                    mBackgroundPaint
                )
            } else {
                canvas.drawRect(
                    0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(),
                    mBackgroundPaint
                )
            }
            // Measure and draw text
            val textBounds = Rect()
            mTextPaint.getTextBounds(mLetter.toString(), 0, 1, textBounds)
            val textWidth = mTextPaint.measureText(mLetter.toString())
            val textHeight = textBounds.height().toFloat()
            canvas.drawText(
                mLetter.toString(), canvas.width / 2f - textWidth / 2f,
                canvas.height / 2f + textHeight / 2f, mTextPaint
            )
        }
    }

    private val textPadding: Float
        get() = 8 * resources.displayMetrics.density

    private fun randomColor(): Int {
        val random = Random()
        val colorsArr: Array<String> = resources.getStringArray(R.array.colors)
        return Color.parseColor(colorsArr[random.nextInt(colorsArr.size)])
    }
}