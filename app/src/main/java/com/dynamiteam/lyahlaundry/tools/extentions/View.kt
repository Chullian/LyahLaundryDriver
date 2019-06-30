package com.dynamiteam.lyahlaundry.tools.extentions

import android.os.Build
import android.view.View
import android.view.View.*
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi

fun View.OnClickListener.setClickListeners(vararg views: View) {
    views.forEach { view -> view.setOnClickListener(this) }
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun View.setDrawable(@DrawableRes drawableRes: Int) {
    background = context.getDrawable(drawableRes)
}

fun View.hide(gone: Boolean = true) {
    visibility = if (gone) GONE else INVISIBLE
}

fun View.show() {
    visibility = VISIBLE
}

fun View.isVisible(): Boolean = visibility == VISIBLE