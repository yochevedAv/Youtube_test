package com.example.youtubetest.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun TextView.colorize(subStringToColorize: String, @ColorRes colorResId: Int) {

    val spannable: Spannable = SpannableString(text)
    val color = ContextCompat.getColor(this.context, colorResId)

    var startIndex = text.indexOf(subStringToColorize, 0, ignoreCase = false)

    while (startIndex >= 0) {
        val endIndex = startIndex + subStringToColorize.length
        spannable.setSpan(
            ForegroundColorSpan(color),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        startIndex = text.indexOf(subStringToColorize, startIndex + 1, ignoreCase = false)
    }

    text = spannable
}