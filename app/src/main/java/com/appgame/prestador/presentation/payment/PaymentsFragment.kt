package com.appgame.prestador.presentation.payment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.appgame.prestador.R
import com.appgame.prestador.databinding.FragmentPaymentsBinding
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.domain.loan.Loan
import com.appgame.prestador.presentation.contacts.adapter.ContactsAdapter
import com.appgame.prestador.utils.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentsFragment : Fragment() {

    private var _binding: FragmentPaymentsBinding? = null
    private val binding get() = _binding
    private var loan: Loan? = null

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

        val adapterC = ContactsAdapter()
        binding?.bottomSheetLayoutInclude?.rvPayments?.apply {
            adapter = adapterC
            layoutManager = LinearLayoutManager(requireContext())
        }
        val array = listOf(
            Contact("41413", "23423", "dsad@gmail", "Prueba", "asdas"),
            Contact("41413", "23423", "dsad@gmail", "Prueba", "asdas"),
            Contact("41413", "23423", "dsad@gmail", "Prueba", "asdas"),
            Contact("41413", "23423", "dsad@gmail", "Prueba", "asdas"),
            Contact("41413", "23423", "dsad@gmail", "Prueba", "asdas"),
            Contact("41413", "23423", "dsad@gmail", "Prueba", "asdas"),
            Contact("41413", "23423", "dsad@gmail", "Prueba", "asdas"),
            Contact("41413", "23423", "dsad@gmail", "Prueba", "asdas"),
            Contact("41413", "23423", "dsad@gmail", "Prueba", "asdas"),
            Contact("41413", "23423", "dsad@gmail", "Prueba", "asdas"),
            Contact("41413", "23423", "dsad@gmail", "Prueba", "asdas"),
            Contact("41413", "23423", "dsad@gmail", "Prueba", "asdas")
        )
        adapterC.submitList(array)


    }

    private fun initView() {

        loan?.let {
            binding?.tvAmount?.text = it.amount
            val interestText = "%${it.interestPercent} de interes"
            binding?.tvInterest?.text = interestText
            binding?.tvPaymentsTime?.text = when (it.paymentsTime) {
                MONTHLY -> "Mensual"
                FORTNIGHTLY -> "Quincenal"
                WEEKLY -> "Semanal"
                DAILY -> "Diario"
                ONCE -> "Una sola excibición"
                else -> ""
            }
            binding?.tvInterestTime?.text = when (it.paymentsTime) {
                YEARLY -> "Anual"
                MONTHLY -> "Mensual"
                FORTNIGHTLY -> "Quincenal"
                WEEKLY -> "Semanal"
                DAILY -> "Diario"
                ONCE -> "Una vez"
                else -> ""
            }
            binding?.tvTypeInterest?.text = when (it.type) {
                SIMPLE -> "Simple"
                COMPOUND -> "Compuesto"
                else -> ""
            }
            if (it.comment.isNotEmpty()){
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
    }


    companion object {
        val TAG: String = PaymentsFragment::class.java.simpleName
        fun newInstance() = PaymentsFragment()
    }


}