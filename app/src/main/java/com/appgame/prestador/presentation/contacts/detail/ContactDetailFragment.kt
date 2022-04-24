package com.appgame.prestador.presentation.contacts.detail

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.appgame.prestador.databinding.FragmentContactDetailBinding
import com.appgame.prestador.model.StatusResult
import com.appgame.prestador.model.contact.Contact
import com.appgame.prestador.model.user.UserIdRequest
import com.appgame.prestador.presentation.loan.adapter.LoanAdapter
import com.appgame.prestador.presentation.loan.create_loan.CreateLoanActivity
import com.appgame.prestador.presentation.payment.PaymentsActivity
import com.appgame.prestador.utils.*
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class ContactDetailFragment : Fragment() {

    private var _binding: FragmentContactDetailBinding? = null
    private val binding get() = _binding
    private val loanAdapter = LoanAdapter()
    private var contact: Contact? = null
    private val dialogLoading: LoadingDialogFragment by lazy { LoadingDialogFragment.newInstance() }
    private val dialogError: ErrorDialogFragment by lazy { ErrorDialogFragment.newInstance() }
    private val viewModel: ContactDetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactDetailBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contact = requireArguments().getParcelable(CONTACT)
        initView()
        initListeners()
        initObservers()

    }

    private fun initView() {


        contact?.let {
            binding?.tvLetterBig?.text = it.name[0].toString()
            binding?.tvLetterMini?.text = it.name[0].toString()
        }
        viewModel.getCurrentUserId()

        binding?.rvLoan?.let {
            it.adapter = loanAdapter
            it.layoutManager = GridLayoutManager(requireContext(), 2)
        }
        binding?.appBarLayout?.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (appBarLayout.totalScrollRange.toFloat() == abs(verticalOffset.toFloat()))
                    initAnimationBar()
                else if (appBarLayout.totalScrollRange.toFloat() / 2 > verticalOffset.toFloat())
                    hideItemsToolBar()
            })
    }

    private fun initListeners() {
        binding?.btnAddLoan?.setOnClickListener {
            startForResultCreateLoan.launch(
                Intent(
                    requireContext(),
                    CreateLoanActivity::class.java
                ).apply {
                    putExtra(CONTACT,contact)
                }
            )
        }

        loanAdapter.clickLoanListener {
            Intent(requireContext(),PaymentsActivity::class.java)
                .apply {
                    putExtra(LOAN,it)
                    startActivity(this)
                }
        }

        loanAdapter.clickAcceptListener {
            viewModel.acceptLoan(it.loanId)
        }

        loanAdapter.clickDeclineListener {
            viewModel.declineLoan(it.loanId)
        }
    }

    private fun initObservers() {
        viewModel.loan.observe(viewLifecycleOwner) {
            when (it.status) {
                StatusResult.LOADING -> initDialog()
                StatusResult.OK -> {
                    binding?.tvCount?.text = "Prestamos: ${it.data?.totalLoans}"
                    binding?.tvCountDebts?.text = "Duedas: ${it.data?.totalDebts}"
                    loanAdapter.submitList(it.data?.loans)
                }
                StatusResult.BAD -> initDialogError(it.message)
            }
        }

        viewModel.currentUserIdState.observe(viewLifecycleOwner){
            it.data?.let { currentUserId ->
                loanAdapter.setCurrentUserId(currentUserId)
                contact?.let { contact ->
                    viewModel.getLoansByContactId(UserIdRequest(contact.userId))
                }

            }

        }

        viewModel.loanUpdatedState.observe(viewLifecycleOwner){
            response ->
            when(response.status){
                StatusResult.LOADING -> initDialog()
                StatusResult.OK -> {
                    requireContext().toastLong(response.message)
                    viewModel.getCurrentUserId()
                }
                StatusResult.BAD -> initDialogError(response.message) { dialogError.dismiss() }
            }
        }

        viewModel.dialogLoading.observe(viewLifecycleOwner) {
            if (!it) dialogLoading.dismiss()
        }
    }

    companion object {
        val TAG: String = ContactDetailFragment::class.java.simpleName
        fun newInstance() = ContactDetailFragment()
    }


    private fun initAnimationBar() {
        binding?.toolbar?.elevation = 0.5f
        binding?.flMini?.apply {
            visibility = View.VISIBLE
            alpha = 0f
            x = -500f
            rotation = 0f
            animate().alpha(1f).translationX(-50f).setDuration(1500).rotationBy(360f).start()
        }
        binding?.tvName?.apply {
            x = -500f
            visibility = View.VISIBLE
            animate()?.translationX(-50f)?.setDuration(1000)?.start()
        }
        binding?.imgSearch?.apply {
            visibility = View.VISIBLE
            alpha = 0f
            animate().alpha(1f).setDuration(500).start()
        }
    }

    private fun hideItemsToolBar() {
        binding?.toolbar?.elevation = 0f
        binding?.flMini?.apply {
            rotation = 0f
            animate().alpha(0f).translationX(-500f).setDuration(1500).rotationBy(360f)
                .withEndAction {
                    visibility = View.GONE
                }.start()
        }
        binding?.tvName?.apply {
            animate()?.translationX(-500f)?.setDuration(1000)?.withEndAction {
                visibility = View.GONE
            }?.start()
        }
        binding?.imgSearch?.apply {
            visibility = View.GONE
            animate().alpha(0f).setDuration(500).start()
        }
    }

    private fun initDialogError(message: String, listener: () -> Unit =  {requireActivity().finish()}) {
        dialogError.showDialog(parentFragmentManager, message, listener = listener)
    }

    private fun initDialog() {
        viewModel.setDialogLoadingTrue()
        dialogLoading.showDialog(parentFragmentManager)
    }

    private val startForResultCreateLoan =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                contact?.let { contact ->
                    viewModel.getLoansByContactId(UserIdRequest(contact.userId))
                }
            }
        }
}