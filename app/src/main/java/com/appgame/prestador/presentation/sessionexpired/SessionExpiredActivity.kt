package com.appgame.prestador.presentation.sessionexpired

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.appgame.prestador.utils.simpleDialogSessionExpired
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SessionExpiredActivity: AppCompatActivity() {

    private val viewModel: SessionExpiredViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.quitUserInfo()

        this.simpleDialogSessionExpired {
            this.finishAndRemoveTask()
        }
    }

}