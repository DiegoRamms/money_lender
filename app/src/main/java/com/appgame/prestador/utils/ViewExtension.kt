package com.appgame.prestador.utils

import android.os.SystemClock
import android.view.View
import com.google.android.material.snackbar.Snackbar


fun View.clickWithDelay(delay: Long = 600L, listener: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < delay) return
            else listener()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun View.showShortSnackBar(text: String = ""){
    Snackbar.make(this,text, Snackbar.LENGTH_SHORT).show()
}

fun View.showLongSnackBar(text: String = ""){
    Snackbar.make(this,text, Snackbar.LENGTH_LONG).show()
}