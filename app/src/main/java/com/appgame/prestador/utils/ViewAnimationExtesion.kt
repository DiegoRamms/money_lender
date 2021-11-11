package com.appgame.prestador.utils

import android.view.View

fun View.fadeAnim(){
    this.animate()?.alpha(0f)?.duration = 1000
}

fun View.translationToRight(){
    this.x = -1000F
    this.alpha = 1f
    this.animate()?.translationX(0f)?.duration = 800
}