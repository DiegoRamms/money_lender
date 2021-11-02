package com.appgame.prestador.utils

import android.os.SystemClock
import android.view.View


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