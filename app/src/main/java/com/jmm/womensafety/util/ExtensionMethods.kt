package com.jmm.womensafety.util

import android.animation.ValueAnimator
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ImageView.loadImageUrl(url: String) {
    val options: RequestOptions = RequestOptions()
        .centerCrop()
        .placeholder(android.R.drawable.ic_menu_report_image)
        .error(android.R.drawable.stat_notify_error)

    Glide.with(this).load(url).apply(options).into(this)
}

fun TextView.animateText(finalValue: Int) {
    val valueAnimator = ValueAnimator.ofInt(0, finalValue)
    valueAnimator.duration = 1500
    valueAnimator.addUpdateListener { valueAnimator ->
        this.text = "${valueAnimator.animatedValue}+"
    }
    valueAnimator.start()
}

fun Uri.getName(context: Context): String? {
    val returnCursor = context.contentResolver.query(this, null, null, null, null)
    val nameIndex = returnCursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    returnCursor?.moveToFirst()
    val fileName = returnCursor?.getString(nameIndex!!)
    returnCursor?.close()
    return fileName
}