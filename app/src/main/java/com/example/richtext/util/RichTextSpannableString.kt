package com.example.richtext.util

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.*
import com.example.richtext.data.RichText
import com.example.richtext.util.StyleSpanFactory.Companion.createStyleSpan
import java.net.URL

class RichTextSpannableString(richText: RichText): SpannableString(richText.text ?: " ") {
    class Builder(private val richText: RichText) {
        private var spannableString = SpannableString(richText.text ?: " ")

        fun setColor(color: String?): Builder {
            color?.let {
                spannableString.setSpan(
                    ForegroundColorSpan(Color.parseColor(it)),
                    0,
                    richText.text?.length ?: 0,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            return this
        }

        fun setStyle(style: String?): Builder {
            style?.let {
                spannableString.setSpan(
                    createStyleSpan(it),
                    0,
                    richText.text?.length ?: 0,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            return this
        }

        fun setSize(size: Float?): Builder {
            size?.let {
                spannableString.setSpan(
                    RelativeSizeSpan(it),
                    0,
                    richText.text?.length ?: 0,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            return this
        }

        fun setImage(image: String?, context: Context): Builder {
            image?.let {
                val bitmap = BitmapFactory.decodeStream(URL(image).openConnection().getInputStream())
                spannableString.setSpan(
                    ImageSpan(context, bitmap),
                    0,
                    1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            return this
        }

        fun build() = spannableString
    }
}