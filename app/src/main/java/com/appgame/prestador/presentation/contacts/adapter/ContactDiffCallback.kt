package com.appgame.prestador.presentation.contacts.adapter

import androidx.recyclerview.widget.DiffUtil
import com.appgame.prestador.model.contact.Contact

object ContactDiffCallback: DiffUtil.ItemCallback<Contact>(){
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }

}