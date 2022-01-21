package com.appgame.prestador.presentation.loan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appgame.prestador.databinding.ItemLoanBinding
import com.appgame.prestador.domain.loan.Loan
import com.appgame.prestador.utils.LoanStatus
import com.appgame.prestador.utils.mapStatus

class LoanAdapter : ListAdapter<Loan, LoanAdapter.ViewHolder>(LoanDiffCallback) {

    private var onItemListener: ((Loan) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLoanBinding =
            ItemLoanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemLoanBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemLoanBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loan: Loan) {
            binding.tvAmount.text = "$${loan.amount}"
            binding.tvInterest.text = "%${loan.interestPercent} de interes"
            binding.tvStatus.text = mapStatus(loan.status)

            binding.cardView.setOnClickListener {
                onItemListener?.let { it(getItem(adapterPosition)) }
            }

        }
    }


    fun clickLoanListener(listener: (Loan) -> Unit) {
        onItemListener = listener
    }


}

