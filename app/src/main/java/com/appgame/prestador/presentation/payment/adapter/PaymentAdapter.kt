package com.appgame.prestador.presentation.payment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appgame.prestador.databinding.ItemPaymentBinding
import com.appgame.prestador.databinding.ItemPaymentPendingBinding
import com.appgame.prestador.domain.payment.Payment

class PaymentAdapter: ListAdapter<Payment, RecyclerView.ViewHolder>(PaymentDiffCallback) {

    private var currentUserId = ""

    companion object {
        const val VIEW_PAYMENT = 1
        const val VIEW_PAYMENT_PENDING = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_PAYMENT)
            ViewHolder(ItemPaymentBinding.inflate(LayoutInflater.from(parent.context),parent,false))
         else
             ViewHolderPending(ItemPaymentPendingBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isAccepted) VIEW_PAYMENT else VIEW_PAYMENT_PENDING
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val payment = getItem(position)
        if (holder.itemViewType == VIEW_PAYMENT){
            (holder as ViewHolder).onBind(payment)
        }else (holder as ViewHolderPending).onBind(payment)
    }

    inner class ViewHolder(private val binding: ItemPaymentBinding): RecyclerView.ViewHolder(binding.root) {

        fun onBind(payment: Payment){
            val amount = "$${payment.amount}"
            binding.tvAmount.text = amount
            binding.tvDate.text = payment.date
        }

    }
    inner class ViewHolderPending(private val binding: ItemPaymentPendingBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(payment: Payment){
            val amount ="Pago de $${payment.amount}"
            binding.tvAmount.text = amount

            binding.btnAcept.visibility = if (payment.user == currentUserId) View.GONE else View.VISIBLE
        }

    }


    fun setCurrentUserId(currentUserId: String){
        this.currentUserId = currentUserId
    }



}