package com.appgame.prestador.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.appgame.prestador.databinding.FragmentMainBinding
import com.appgame.prestador.model.StatusResult
import com.appgame.prestador.model.transaction.Transaction
import com.appgame.prestador.presentation.main.adapter.TransactionAdapter
import com.appgame.prestador.utils.ErrorDialogFragment
import com.appgame.prestador.utils.LoadingDialogFragment
import com.appgame.prestador.utils.SwipeHelper
import com.appgame.prestador.utils.simpleDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding
    private val viewModel: MainViewModel by viewModels()
    private val loadingDialogFragment by lazy { LoadingDialogFragment.newInstance() }
    private val errorDialogFragment by lazy { ErrorDialogFragment.newInstance() }
    private var currentList: ArrayList<Transaction>? = null
    private var transactionsAdapter: TransactionAdapter = TransactionAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeMainInfo()
        observeLoadingState()
        initListeners()


    }

    private fun initView() {
        binding?.tvGreeting?.text = "Hola Diego"
        val swipeHelper = SwipeHelper { position ->
            currentList?.let { list ->
                list.removeAt(position)
            }
            transactionsAdapter.notifyItemRemoved(position)
        }

        //val itemTouchHelper = ItemTouchHelper(swipeHelper)

        binding?.rvTransactions?.apply {
            adapter = transactionsAdapter
            layoutManager = GridLayoutManager(requireContext(),1)
            //itemTouchHelper.attachToRecyclerView(this)
        }


    }

    private fun initListeners() {
        binding?.swipe?.setOnRefreshListener {
            binding?.swipe?.isRefreshing = false
            viewModel.getMainDetail()
        }
    }

    private fun observeMainInfo() {
        viewModel.mainDetailState.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                StatusResult.LOADING -> {initDialog()}
                StatusResult.OK -> {
                    val data = response.data
                    data?.let {
                        mainDetail ->
                        val textProgressLoans = "Te han pagadp el %${mainDetail.loansPercentagePaid} de tus prestamos activos"
                        val textProgressDebts = "Haz pagado el %${mainDetail.debtsPercentagePaid} de tus deudas activas"
                        binding?.tvGreeting?.text = mainDetail.greeting
                        "$${mainDetail.loansAmount}".also { binding?.tvAmountLoans?.text = it }
                        "$${mainDetail.debtsAmount}".also { binding?.tvAmountDebts?.text = it }
                        binding?.tvPercentLoanPayment?.text = textProgressLoans
                        binding?.tvPercentDebtsPayment?.text = textProgressDebts
                        binding?.progressLoan?.progress = mainDetail.loansPercentagePaid.toInt()
                        binding?.progressDebts?.progress = mainDetail.debtsPercentagePaid.toInt()
                        binding?.tvLoansCount?.text = mainDetail.loansCount
                        binding?.tvDebtsCount?.text = mainDetail.debtsCount
                        binding?.tvDateNextPayment?.text = mainDetail.loanNearDue.dateLimit
                        binding?.tvLoanDueAmount?.text = "$${mainDetail.loanNearDue.amount}"
                        transactionsAdapter.submitList(mainDetail.transactions)

                    }

                }
                StatusResult.BAD -> {
                    requireContext().simpleDialog("Error", response.message, { dialog, _ ->
                        dialog.dismiss()
                    }, null)
                }
            }
        }


    }

    private fun observeLoadingState(){
        viewModel.dialogLoadingState.observe(viewLifecycleOwner){
            if (!it) loadingDialogFragment.dismiss()
        }
    }

    private fun initDialog() {
        viewModel.setDialogLoadingTrue()
        loadingDialogFragment.showDialog(parentFragmentManager)
    }

    companion object {
        val TAG: String = MainFragment::class.java.simpleName

        fun newInstance() = MainFragment()

    }

}