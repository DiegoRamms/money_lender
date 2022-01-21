package com.appgame.prestador.presentation.loan.create_loan

import android.app.Activity.RESULT_OK
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.appgame.prestador.R
import com.appgame.prestador.databinding.FragmentCreateLoanBinding
import com.appgame.prestador.domain.StatusResult
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.domain.loan.CreateLoanRequest
import com.appgame.prestador.domain.loan.DifferenceTime
import com.appgame.prestador.domain.user.AmountError
import com.appgame.prestador.utils.*
import com.appgame.prestador.utils.date.DATE_BASE_FORMAT
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.math.floor

@AndroidEntryPoint
class CreateLoanFragment : Fragment() {

    private var _binding: FragmentCreateLoanBinding? = null
    private val binding get() = _binding
    private val dialogLoading by lazy { LoadingDialogFragment.newInstance() }
    private val dialogError by lazy { ErrorDialogFragment.newInstance() }
    private val viewModel: CreateLoanViewModel by viewModels()
    private var startDate: Date? = null
    private var limitDate: Date? = null
    private var paymentsTime = ""
    private var interestTime = ""
    private var contact: Contact? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateLoanBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contact = requireArguments().getParcelable(CONTACT)

        initView()
        initObservers()
        initListeners()
    }


    private fun initView() {

    }

    private fun initListeners() {

        binding?.slider?.addOnChangeListener { _, value, _ ->
            val percent = "%${value.toInt()}"
            binding?.tvInterestValue?.text = percent
        }

        binding?.btnCreate?.clickWithDelay {
            contact?.let {
                if (it.contactId != null) {
                    viewModel.createLoan(getValues(it.contactId))
                } else initDialogError("Error inesperado")
            }
        }

        binding?.tvDate?.clickWithDelay {
            showPicker()
        }



        binding?.chipGroup?.setOnCheckedChangeListener { _, checkedId ->
            paymentsTime = when(checkedId){
                R.id.chip_month -> MONTHLY
                R.id.chip_fortnightly -> FORTNIGHTLY
                R.id.chip_weekly -> WEEKLY
                R.id.chip_daily -> DAILY
                R.id.chip_once -> ONCE
                else -> ""
            }
        }


        binding?.radioGroupTime?.setOnCheckedChangeListener { _, i ->
            interestTime = when(i){
                R.id.radio_month -> MONTHLY
                R.id.radio_year -> YEARLY
                R.id.radio_once -> ONCE
                else -> ""
            }
        }



    }

    private fun initObservers() {
        viewModel.loan.observe(viewLifecycleOwner, {
            when (it.status) {
                StatusResult.LOADING -> initDialog()
                StatusResult.OK -> {
                    requireContext().simpleDialog("Prestamo", it.message, { dialogInterface, _ ->
                        dialogInterface.dismiss()
                        activity?.setResult(RESULT_OK)
                        activity?.finish()
                    })
                }
                StatusResult.BAD -> initDialogError(it.message)
            }
        })

        viewModel.dialogLoading.observe(viewLifecycleOwner,{
            if (!it) dialogLoading.dismiss()
        })

        viewModel.formError.observe(viewLifecycleOwner, {
            when (it) {
                is AmountError -> {
                    binding?.edtAmount?.error = it.description
                }
                else -> {requireContext().simpleDialog(message = it.description)}
            }
        })
    }

    private fun showPicker() {
        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Fecha inicial")
                .setSelection(
                    Pair(
                        MaterialDatePicker.todayInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds() + 1
                    )
                )
                .build()

        dateRangePicker.addOnPositiveButtonClickListener {
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("GTM"))
            val calendar2 = Calendar.getInstance(TimeZone.getTimeZone("GTM"))
            calendar.timeInMillis = it.first
            calendar2.timeInMillis = it.second
            val startDateString =  SimpleDateFormat(DATE_BASE_FORMAT, Locale("es", "ES"))
                .format(calendar.time)
                .replace(".", "")
            val limitDateString = SimpleDateFormat(DATE_BASE_FORMAT, Locale("es", "ES"))
                .format(calendar2.time)
                .replace(".", "")
            val textDates = "$startDateString - $limitDateString"

            startDate = calendar.time
            limitDate = calendar2.time

            val differenceTime = getDifferenceTime(startDate!!,limitDate!!)

            binding?.clInterestInfo?.visibility = View.VISIBLE
            binding?.chipMonth?.visibility = if (differenceTime.days < 31) View.GONE else View.VISIBLE
            binding?.chipFortnightly?.visibility = if (differenceTime.weeks < 2) View.GONE else View.VISIBLE
            binding?.chipWeekly?.visibility = if (differenceTime.weeks < 1)View.GONE else View.VISIBLE
            binding?.radioYear?.visibility = if (differenceTime.years < 1)View.GONE else View.VISIBLE
            binding?.chipGroup?.clearCheck()
            binding?.radioGroupTime?.clearCheck()
            binding?.tvDate?.text = textDates

        }

        dateRangePicker.show(parentFragmentManager, "datepicker")
    }

    private fun getValues(contactId: String): CreateLoanRequest {
        val amount = binding?.edtAmount?.text.toString()
        val interestPercent = binding?.slider?.value.toString()
        val comment = binding?.edtComment?.text.toString()

        return CreateLoanRequest(contactId, amount, startDate, limitDate, paymentsTime, interestPercent, interestTime,comment)
    }

    private fun getDifferenceTime(dateStart: Date, dateLimit: Date): DifferenceTime{
        val millisecondMinute: Double = 1000.00 * 60
        val millisecondHour = millisecondMinute * 60
        val millisecondDay = millisecondHour * 24

        val differenceTime = dateLimit.time - dateStart.time

        val days = floor(differenceTime/millisecondDay)
        val weeks = floor(days/7)
        val months = floor(days/31)
        val years = floor(months/12)

        return DifferenceTime(days = days,weeks = weeks,months = months,years = years)
    }


    private fun initDialog() {
        viewModel.setDialogLoadingTrue()
        dialogLoading.showDialog(parentFragmentManager)
    }

    private fun initDialogError(message: String) {
        dialogError.showDialog(parentFragmentManager, message)
    }

    companion object {
        val TAG: String = CreateLoanFragment::class.java.simpleName

        fun newInstance() = CreateLoanFragment()
    }

}