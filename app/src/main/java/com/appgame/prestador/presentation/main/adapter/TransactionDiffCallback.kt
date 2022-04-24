package com.appgame.prestador.presentation.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.appgame.prestador.model.transaction.Transaction

object TransactionDiffCallback: DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.id == newItem.id
    }
}