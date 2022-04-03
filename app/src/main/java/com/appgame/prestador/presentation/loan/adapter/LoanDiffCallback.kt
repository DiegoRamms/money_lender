package com.appgame.prestador.presentation.loan.adapter

import androidx.recyclerview.widget.DiffUtil
import com.appgame.prestador.model.loan.Loan

object LoanDiffCallback: DiffUtil.ItemCallback<Loan>() {
    override fun areItemsTheSame(oldItem: Loan, newItem: Loan): Boolean {
        return oldItem.loanId == newItem.loanId
    }

    override fun areContentsTheSame(oldItem: Loan, newItem: Loan): Boolean {
        return oldItem == newItem
    }
}