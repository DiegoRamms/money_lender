package com.appgame.prestador.presentation.loan.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appgame.prestador.R
import com.appgame.prestador.databinding.ItemLoanBinding
import com.appgame.prestador.model.loan.Loan
import com.appgame.prestador.utils.LoanStatus
import com.appgame.prestador.utils.mapStatus

class LoanAdapter : ListAdapter<Loan, LoanAdapter.ViewHolder>(LoanDiffCallback) {

    private var onItemListener: ((Loan) -> Unit)? = null
    private var onAcceptListener: ((Loan) -> Unit)? = null
    private var onDeclineListener: ((Loan) -> Unit)? = null
    private var currentUserId: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLoanBinding =
            ItemLoanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemLoanBinding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemLoanBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loan: Loan) {
            binding.tvAmount.text = "$${loan.amount}"
            binding.tvInterest.text = "%${loan.interestPercent} de interes"
            binding.tvStatus.text = mapStatus(loan.status)

            currentUserId?.let { id ->
                binding.chip.text = if (id == loan.userMoneyLender) "Prestamo" else "Deuda"
                binding.chip.chipIcon = ContextCompat.getDrawable(
                    context,
                    if (id == loan.userMoneyLender) R.drawable.ic_baseline_trending_up_24 else R.drawable.ic_baseline_trending_down_24
                )
                binding.chip.chipBackgroundColor = ContextCompat.getColorStateList(
                    context,
                    if (id == loan.userMoneyLender) R.color.green_light else R.color.red_light
                )
                binding.btnAccept.visibility =
                    if (id == loan.userMoneyLender) View.GONE else View.VISIBLE
                binding.btnDecline.isEnabled = !(id == loan.userMoneyLender)

                binding.btnDecline.text =
                    if (id == loan.userMoneyLender) "Pendiente" else "Rechazar"
                binding.btnDecline.setTextColor(
                    ContextCompat.getColorStateList(
                        context,
                        if (id == loan.userMoneyLender) R.color.yellow else R.color.white
                    )
                )
            }


            binding.tvPending.visibility =
                if (loan.status == LoanStatus.PENDING.value) View.VISIBLE else View.GONE

            binding.clPending.visibility =
                if (loan.status == LoanStatus.PENDING.value) View.VISIBLE else View.GONE

            binding.cardView.isEnabled = (loan.status != LoanStatus.PENDING.value)




            binding.cardView.setOnClickListener {
                onItemListener?.let { it(getItem(adapterPosition)) }
            }

            binding.btnAccept.setOnClickListener {
                onAcceptListener?.let { it(getItem(adapterPosition)) }
            }

            binding.btnDecline.setOnClickListener {
                onDeclineListener?.let { it(getItem(adapterPosition)) }
            }

        }
    }

    fun setCurrentUserId(currentUserId: String) {
        this.currentUserId = currentUserId
    }

    fun clickLoanListener(listener: (Loan) -> Unit) {
        onItemListener = listener
    }

    fun clickAcceptListener(listener: (Loan) -> Unit) {
        onAcceptListener = listener
    }

    fun clickDeclineListener(listener: (Loan) -> Unit) {
        onDeclineListener = listener
    }


}

