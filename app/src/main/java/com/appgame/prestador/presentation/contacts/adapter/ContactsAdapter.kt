package com.appgame.prestador.presentation.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appgame.prestador.databinding.ItemContactBinding
import com.appgame.prestador.domain.contact.ContactDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactsAdapter @Inject constructor() : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    private var contactDTOS: List<ContactDTO>? = null
    private var onItemListener: ((ContactDTO) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactDTOS!![position]
        holder.textFirstLetter.text = contact.name[0].toString()
        holder.textName.text = contact.name
    }

    override fun getItemCount(): Int {
        return contactDTOS?.size ?: 0
    }

    inner class ViewHolder(binding: ItemContactBinding): RecyclerView.ViewHolder(binding.root) {
        val textName = binding.textViewName
        val textFirstLetter = binding.textFirstLetter

        init {
            binding.root.setOnClickListener {
               onItemListener?.let { it(contactDTOS!![adapterPosition]) }
            }
        }
    }


    fun setOnItemListener(listener : ((ContactDTO) -> Unit)){
        onItemListener = listener
    }


    fun setContacts(contactDTOS: List<ContactDTO>){
        this.contactDTOS = contactDTOS
        notifyDataSetChanged()
    }

}
