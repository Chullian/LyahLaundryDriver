package com.dynamiteam.lyahlaundrydriver.tools.extentions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onChange(cb: (Editable) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            cb(s)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun EditText.onFocusChanged(cb: (Boolean) -> Unit) {
    this.setOnFocusChangeListener { v, hasFocus -> cb(hasFocus) }
}

fun EditText.text() = this.text.toString()
