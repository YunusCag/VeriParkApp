package com.yunuscagliyan.veriparkapp.common.extension

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText


inline fun TextInputEditText.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            listener(s.toString())
        }

        override fun afterTextChanged(s: Editable?) {
        }

    })
}