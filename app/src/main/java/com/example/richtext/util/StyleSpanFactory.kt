package com.example.richtext.util

import android.graphics.Typeface
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan

class StyleSpanFactory {
    companion object {
        /**
         * Returns a Span by [style].
         * @param style A name of [TextStyle]
         */
        fun createStyleSpan(style: String): Any {
            return when (style) {
                TextStyle.BOLD.name -> StyleSpan(Typeface.BOLD)
                TextStyle.UNDERLINE.name -> UnderlineSpan()
                TextStyle.STRIKE_THROUGH.name -> StrikethroughSpan()
                else -> StyleSpan(Typeface.NORMAL)
            }
        }
    }
}