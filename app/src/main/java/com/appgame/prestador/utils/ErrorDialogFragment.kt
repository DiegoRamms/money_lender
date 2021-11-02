package com.appgame.prestador.utils

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.appgame.prestador.R
import com.appgame.prestador.databinding.FragmentDialogErrorBinding

class ErrorDialogFragment private constructor() : DialogFragment() {

    private var _binding: FragmentDialogErrorBinding? = null
    private val binding get() = _binding
    private var listener: (() ->Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentDialogErrorBinding.inflate(layoutInflater)

        val dialog = Dialog(requireContext(),R.style.DialogTheme)
        dialog.setCancelable(false)

        binding?.let {
            dialog.setContentView(it.root)
        }



        return dialog

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.tvError?.setOnClickListener {
            listener
            dismiss()
        }
    }


    fun clickRetry(listener: () -> Unit){
        this.listener = listener
    }

    fun setMessage(message: String) {
        binding?.tvError?.text = message
    }


    companion object {

        val TAG: String = ErrorDialogFragment::class.java.simpleName

        fun newInstance() = ErrorDialogFragment()


    }

}