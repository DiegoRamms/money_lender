package com.appgame.prestador.presentation.payment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.appgame.prestador.domain.payment.Payment

object PaymentDiffCallback: DiffUtil.ItemCallback<Payment>() {
    override fun areItemsTheSame(oldItem: Payment, newItem: Payment): Boolean {
        return oldItem.paymentId == newItem.paymentId
    }

    override fun areContentsTheSame(oldItem: Payment, newItem: Payment): Boolean {
        return oldItem == newItem
    }
}