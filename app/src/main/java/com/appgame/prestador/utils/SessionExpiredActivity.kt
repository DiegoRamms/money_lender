package com.appgame.prestador.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appgame.prestador.R

class SessionExpiredActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.simpleDialogSessionExpired {
            this.finishAndRemoveTask()
        }
    }

}