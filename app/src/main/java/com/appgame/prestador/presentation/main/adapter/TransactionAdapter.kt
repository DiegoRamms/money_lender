package com.appgame.prestador.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appgame.prestador.databinding.ItemPendingTransactionBinding
import com.appgame.prestador.databinding.ItemTransactionBinding
import com.appgame.prestador.model.transaction.Transaction
import com.appgame.prestador.model.transaction.TransactionType

class TransactionAdapter: ListAdapter<Transaction,RecyclerView.ViewHolder>(TransactionDiffCallback) {
    companion object{
        const val TRANSACTION = 0
        const val TRANSACTION_PENDING = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TRANSACTION -> ViewHolder(ItemTransactionBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            else -> ViewHolderPending(ItemPendingTransactionBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isAccepted)  TRANSACTION else TRANSACTION_PENDING
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val transaction = getItem(position)
        if (holder.itemViewType == TRANSACTION) (holder as ViewHolder).onBind(transaction)
        else (holder as ViewHolderPending).onBind(transaction)
    }

    class ViewHolder(private val binding: ItemTransactionBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(transaction: Transaction){
            binding.tvName.text = transaction.name
            binding.tvFirstLetter.text = transaction.name[0].toString()
            binding.tvAmount.text = "$${transaction.amount}"
            binding.tvType.text = if (transaction.type == TransactionType.PAYMENT) "Recibí" else "Pague"
            binding.tvDate.text = transaction.date
        }
    }
    class ViewHolderPending(private val binding: ItemPendingTransactionBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(transaction: Transaction){
            binding.tvName.text = transaction.name
            binding.tvFirstLetter.text = transaction.name[0].toString()
            binding.tvAmount.text = transaction.amount
        }
    }


}