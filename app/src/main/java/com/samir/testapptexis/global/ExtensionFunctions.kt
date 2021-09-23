package com.samir.testapptexis.global

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast

fun Context.showToast(msg: String?, time: Int = Toast.LENGTH_LONG) {
    if (msg?.isNotBlank() == true) {
        Toast.makeText(this, msg, time).show()
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

//Hide Soft Keyboard ***************
fun Activity.hideKeyboard() {
    try {
        val mInputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var mView = this.currentFocus
        if (mView == null) {
            mView = View(this)
        }
        mInputMethodManager.hideSoftInputFromWindow(mView.windowToken, 0)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}