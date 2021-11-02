package com.appgame.prestador.presentation.contacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appgame.prestador.databinding.ItemContactToAcceptBinding
import com.appgame.prestador.domain.contact.Contact

class ContactsPendingAdapter: ListAdapter<Contact, ContactsPendingAdapter.ViewHolder>(ContactDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = ItemContactToAcceptBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact)
    }


    class ViewHolder(private val binding: ItemContactToAcceptBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact){
            binding.tvName.text = contact.name
            binding.tvCode.text = contact.code
        }
    }


}