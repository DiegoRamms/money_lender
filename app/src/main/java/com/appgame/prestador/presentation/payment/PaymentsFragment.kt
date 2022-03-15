package com.appgame.prestador.presentation.payment

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.appgame.prestador.R
import com.appgame.prestador.databinding.FragmentPaymentsBinding
import com.appgame.prestador.domain.StatusResult
import com.appgame.prestador.domain.loan.Loan
import com.appgame.prestador.domain.loan.LoanIdRequest
import com.appgame.prestador.domain.payment.Payment
import com.appgame.prestador.presentation.payment.adapter.PaymentAdapter
import com.appgame.prestador.utils.*
import com.appgame.prestador.utils.date.DATE_BASE_FORMAT
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class PaymentsFragment : Fragment() {

    private var _binding: FragmentPaymentsBinding? = null
    private val binding get() = _binding
    private var loan: Loan? = null
    private val dialogLoading by lazy { LoadingDialogFragment.newInstance() }
    private val dialogError by lazy { ErrorDialogFragment.newInstance() }
    private val dialogCreatePayment by lazy { CreatePaymentDialogFragment.newInstance() }
    private val viewModel: PaymentsViewModel by viewModels()
    private val paymentAdapter = PaymentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loan = requireArguments().getParcelable(LOAN)
        initView()
        initListeners()
        initObservers()
        loan?.loanId?.let {
            viewModel.getLoanPaymentDetail(LoanIdRequest(loanId = it))
        }

        binding?.bottomSheetLayoutInclude?.rvPayments?.apply {
            adapter = paymentAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun initView() {

        loan?.let { it ->
            val amountText = "$${it.amount}"
            binding?.tvAmount?.text = amountText
            binding?.tvStatus?.text = mapStatus(it.status)
            binding?.tvPaymentsTime?.text = when (it.paymentsTime) {
                MONTHLY -> "Mensual"
                FORTNIGHTLY -> "Quincenal"
                WEEKLY -> "Semanal"
                DAILY -> "Diario"
                ONCE -> "Una sola excibición"
                else -> ""
            }
            ("%" + it.interestPercent + " " + when (it.interestTime) {
                YEARLY -> "Anual"
                MONTHLY -> "Mensual"
                FORTNIGHTLY -> "Quincenal"
                WEEKLY -> "Semanal"
                DAILY -> "Diario"
                ONCE -> "Una vez"
                else -> ""
            }).also { result -> binding?.tvInterest?.text = result }



            binding?.tvTypeInterest?.text = when (it.type) {
                SIMPLE -> "Simple"
                COMPOUND -> "Compuesto"
                else -> ""
            }
            if (it.comment.isNotEmpty()) {
                binding?.tvComment?.visibility = View.VISIBLE
                binding?.tvComment?.text = it.comment
            }

            binding?.tvDateStart?.text = it.dateStart
            binding?.tvDateLimit?.text = it.dateLimit
        }
    }

    private fun initListeners() {
        binding?.bottomSheetLayoutInclude?.bottomSheetLayout?.let {
            val sheetBehavior = BottomSheetBehavior.from(it)
            sheetBehavior.addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    binding?.bottomSheetLayoutInclude?.imgArrow?.rotationX = slideOffset * 180
                    binding?.bottomSheetLayoutInclude?.fabAddPayment?.apply {
                        alpha = slideOffset
                        visibility = if (slideOffset == 0f) View.GONE else View.VISIBLE
                    }

                }
            })
        }

        binding?.bottomSheetLayoutInclude?.fabAddPayment?.setOnClickListener {
            val arguments = Bundle().apply {
                putString(LOAN_ID, loan?.loanId)
            }
            dialogCreatePayment.arguments = arguments
            dialogCreatePayment.showDialog(parentFragmentManager)
        }

        dialogCreatePayment. clickAddPayment { createPaymentRequest ->
            viewModel.createPayment(createPaymentRequest)
        }

    }

    private fun initObservers() {
        viewModel.paymentDetail.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                StatusResult.LOADING -> initDialog()
                StatusResult.OK -> {
                    response?.data?.let { paymentInfo ->
                        binding?.tvIsPaidOut?.visibility =
                            if (paymentInfo.isPaidOut) View.VISIBLE else View.GONE

                        binding?.tvTotalToPay?.text = "Total a pagar $${paymentInfo.totalToPay}"
                        binding?.tvAmount?.text = requireContext().getString(
                            R.string.amount_with_sign,
                            paymentInfo.progressPayText
                        )
                        if (paymentInfo.isPaidOut) binding?.tvTotalToPay?.paintFlags =
                            Paint.STRIKE_THRU_TEXT_FLAG;

                        binding?.progressCircular?.progress =
                            paymentInfo.progressPayPercentage.toInt()

                        binding?.tvNextPayment?.text = paymentInfo.nextPayTime
                        paymentAdapter.setCurrentUserId(paymentInfo.currentUserId)
                        paymentAdapter.submitList(paymentInfo.payments)
                    }
                }
                StatusResult.BAD -> {
                    requireContext().simpleDialog(
                        message = response.message,
                        clickPositive = { dialog, _ ->
                            dialog.dismiss()
                            activity?.finish()
                        })//initDialogError(response.message)
                }
            }
        }

        viewModel.payment.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                StatusResult.LOADING -> initDialog()
                StatusResult.OK -> {
                    response.data?.loanId?.let {viewModel.getLoanPaymentDetail(LoanIdRequest(it))}
                    context?.simpleDialog(title = "Creado", message = response.message)
                    dialogCreatePayment.dismiss()
                }
                StatusResult.BAD -> {
                    requireContext().simpleDialog(message = response.message)
                }
            }
        }

        viewModel.dialogLoading.observe(viewLifecycleOwner) {
            if (!it) dialogLoading.dismiss()
        }
    }

    private fun initDialog() {
        viewModel.setDialogLoadingTrue()
        dialogLoading.showDialog(parentFragmentManager)
    }

    private fun initDialogError(message: String) {
        if (dialogError.isAdded) dialogError.onDetach()
        dialogError.showDialog(parentFragmentManager, message)
    }

    companion object {
        val TAG: String = PaymentsFragment::class.java.simpleName
        fun newInstance() = PaymentsFragment()
    }


}