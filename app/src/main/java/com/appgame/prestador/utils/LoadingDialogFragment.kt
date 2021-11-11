package com.appgame.prestador.utils


import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.appgame.prestador.R
import com.appgame.prestador.databinding.FragmentDialogLoadingBinding
import javax.annotation.Nullable

class LoadingDialogFragment : DialogFragment() {

    private var _bindingDialogFragment: FragmentDialogLoadingBinding? = null
    private val bindingDialogFragment get() = _bindingDialogFragment

    override fun onCreateDialog(@Nullable savedInstanceState: Bundle?): Dialog {
        _bindingDialogFragment = FragmentDialogLoadingBinding.inflate(layoutInflater)
        bindingDialogFragment?.tvLoading?.text = "Cargando"

        val dialog = Dialog(requireContext(),R.style.DialogTheme)
        dialog.window?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(requireContext(),R.color.transparent_black)))
        dialog.setCancelable(false)
        bindingDialogFragment?.let {
            dialog.setContentView(it.root)
        }

        return dialog
    }

    fun showDialog(fragmentManager: FragmentManager){
        if (!this.isAdded){
            show(fragmentManager, TAG)
        }
    }

    override fun dismiss() {
        try {
            super.dismiss()
        }catch (e: Exception){}

    }
    companion object {
        val TAG: String = LoadingDialogFragment::class.java.simpleName
        fun newInstance() = LoadingDialogFragment()
    }




}