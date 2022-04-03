package com.appgame.prestador.presentation.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appgame.prestador.databinding.ItemContactToAcceptBinding
import com.appgame.prestador.model.contact.Contact
import com.appgame.prestador.utils.clickWithDelay

class ContactsPendingAdapter :
    ListAdapter<Contact, ContactsPendingAdapter.ViewHolder>(ContactDiffCallback) {

    private var onAddListener: ((Contact) -> Unit)? = null
    private var onDeleteListener: ((Contact) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemContactToAcceptBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact)
    }


    inner class ViewHolder(private val binding: ItemContactToAcceptBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.tvName.text = contact.name
            binding.tvCode.text = contact.code
        }

        init {
            binding.btnAdd.clickWithDelay {
                onAddListener?.let { it(getItem(adapterPosition)) }
            }
            binding.tvCancel.clickWithDelay {
                onDeleteListener?.let { it(getItem(adapterPosition)) }
            }
        }
    }

    fun addOnclickListener(listener: (Contact) -> Unit) {
        onAddListener = listener
    }

    fun deleteRequestClickListener(listener: (Contact) -> Unit) {
        onDeleteListener = listener
    }

}