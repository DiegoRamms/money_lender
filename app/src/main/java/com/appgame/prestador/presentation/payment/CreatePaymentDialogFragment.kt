package com.appgame.prestador.presentation.payment

import android.app.Dialog
import android.content.DialogInterface
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.appgame.prestador.databinding.FragmentDialogCreatePaymentBinding
import com.appgame.prestador.model.payment.CreatePaymentRequest
import com.appgame.prestador.utils.LOAN_ID
import com.appgame.prestador.utils.date.DATE_BASE_FORMAT
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*


class CreatePaymentDialogFragment: DialogFragment() {

    private var _binding: FragmentDialogCreatePaymentBinding? = null
    private val binding get() = _binding
    private var clickAddPayment: ((CreatePaymentRequest) -> Unit)? = null
    private var loanId = ""
    private var date: Date? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentDialogCreatePaymentBinding.inflate(layoutInflater)

        requireArguments().getString(LOAN_ID)?.let { id -> loanId = id }

        val dialog = Dialog(requireContext())

        dialog.setCancelable(false)
        binding?.let {
            dialog.setContentView(it.root)
        }

        initListeners()



        return dialog

    }

    private fun initListeners(){

        binding?.tvDate?.setOnClickListener {
            showPicker()
        }

        binding?.btnAddPayment?.setOnClickListener {
            val amount = binding?.edtAmount?.text.toString()

            if (date == null){
                Toast.makeText(requireContext(), "Elige fecha", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (amount.isEmpty()){
                Toast.makeText(requireContext(), "Escrina monto", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            date?.let {
                clickAddPayment?.invoke( CreatePaymentRequest(loanId,amount = amount,date = it ))
            }

        }
    }

    fun showDialog(fragmentManager: FragmentManager){
        if (!this.isAdded){
            show(fragmentManager, TAG)
        }
    }

    fun clickAddPayment(listener : ((CreatePaymentRequest) -> Unit)){
        clickAddPayment = listener
    }


    private fun showPicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Fecha inicial")
                .setSelection(
                    MaterialDatePicker.todayInUtcMilliseconds(),
                )
                .build()

        datePicker.addOnPositiveButtonClickListener {

            val calendar = Calendar.getInstance(TimeZone.getTimeZone("GTM"))
            calendar.timeInMillis = it
            val startDateString =  SimpleDateFormat(DATE_BASE_FORMAT, Locale("es", "ES"))
                .format(calendar.time)
                .replace(".", "")

            binding?.tvDate?.text = startDateString
            date = calendar.time

        }

        datePicker.show(parentFragmentManager, "datepicker")
    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
    }

    override fun onDismiss(dialog: DialogInterface) {
        try {
            super.dismiss()
        }catch (e: Exception){}

    }

    companion object {
        val TAG: String = CreatePaymentDialogFragment::class.java.simpleName
        fun newInstance() = CreatePaymentDialogFragment()
    }
}