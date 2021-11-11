package com.appgame.prestador.utils

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.appgame.prestador.R
import com.appgame.prestador.databinding.FragmentDialogErrorBinding

class ErrorDialogFragment : DialogFragment() {

    private var _binding: FragmentDialogErrorBinding? = null
    private val binding get() = _binding
    private var listener: (() -> Unit)? = null
    private var textMessage: String = ""
    private var textButton: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentDialogErrorBinding.inflate(layoutInflater)
        val dialog = Dialog(requireContext(), R.style.DialogThemeAnim)

        if (textMessage.isNotEmpty()) this.binding?.tvError?.text = textMessage
        if (textButton.isNotEmpty()) this.binding?.tvError?.text = textButton

        dialog.setCancelable(false)
        binding?.let {
            dialog.setContentView(it.root)
        }
        binding?.tvRetry?.setOnClickListener {
            listener?.invoke()
            dismiss()
        }

        return dialog
    }

    fun showDialog(
        fragmentManager: FragmentManager,
        textMessage: String = "",
        textButton: String = "",
        listener: (() -> Unit)? = null
    ) {
        this.textMessage = textMessage
        this.textButton = textButton
        if (listener != null) {
            this.listener = listener
        }
        if (!this.isAdded) {
            show(fragmentManager, TAG)
        }
    }

    companion object {
        val TAG: String = ErrorDialogFragment::class.java.simpleName
        fun newInstance() = ErrorDialogFragment()
    }


}