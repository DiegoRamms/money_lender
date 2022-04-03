package com.appgame.prestador.presentation.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appgame.prestador.databinding.ItemContactBinding
import com.appgame.prestador.model.contact.Contact


class ContactsAdapter  : ListAdapter<Contact, ContactsAdapter.ViewHolder>(ContactDiffCallback) {

    private var onItemListener: ((Contact) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = getItem(position)
        holder.textFirstLetter.text = contact.name[0].toString()
        holder.textName.text = contact.name
    }


    inner class ViewHolder(binding: ItemContactBinding): RecyclerView.ViewHolder(binding.root) {
        val textName = binding.textViewName
        val textFirstLetter = binding.textFirstLetter

        init {
            binding.root.setOnClickListener {
               onItemListener?.let { it(getItem(adapterPosition)) }
            }
        }
    }


    fun setOnItemListener(listener : ((Contact) -> Unit)){
        onItemListener = listener
    }



}
