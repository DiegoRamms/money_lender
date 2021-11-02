package com.appgame.prestador.utils

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.appgame.prestador.presentation.login.LoginActivity


fun Context.toastShort(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.toastLong(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}


fun Context.simpleDialog(
    title: String? = "Alerta",
    message: String? = "",
    clickPositive: (DialogInterface, Int) -> Unit,
    clickNegative: ((DialogInterface, Int) -> Unit)? = null,
    //eventDismiss: ((DialogInterface, Int) -> Unit)? = null
    isCancelable: Boolean = false
) {
    val dialog: AlertDialog = AlertDialog.Builder(this).create()

    dialog.apply {
        setTitle(title)
        setMessage(message)
        setCancelable(isCancelable)
        setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar", clickPositive)

        if (clickNegative != null) setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", clickNegative)


    }
    dialog.show()
}

fun Context.simpleDialogSessionExpired(eventDismiss: ((DialogInterface) -> Unit)) {
    val dialog: AlertDialog = AlertDialog.Builder(this).create()
    dialog.apply {
        setTitle("Tu sesión expiro")
        setMessage("Su sesión ha expirado, lo redirigiremos al inicio de sesión")
        setCancelable(false)
        setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar") { dialog, _ ->
            dialog.dismiss()
            Intent(context, LoginActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                putExtra("DELETE_JWT", true)
                startActivity(this)
            }
        }
        setOnCancelListener { eventDismiss.invoke(it) }
    }
    dialog.show()
}

