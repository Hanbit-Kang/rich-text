package com.example.richtext.util

import android.content.Context
import android.text.SpannableStringBuilder
import com.example.richtext.data.RichText

class SpannableStringBuilderProvider {
    companion object {
        fun getSpannableStringBuilder(richTextList: List<RichText>, context: Context): SpannableStringBuilder {
            val spannableStringBuilder = SpannableStringBuilder()

            richTextList.forEach { richText ->
                val spannableString = RichTextSpannableString.Builder(richText)
                    .setColor(richText.color)
                    .setStyle(richText.style)
                    .setSize(richText.size)
                    .setImage(richText.image, context)
                    .build()
                spannableStringBuilder.append(spannableString)
            }

            return spannableStringBuilder
        }
    }
}