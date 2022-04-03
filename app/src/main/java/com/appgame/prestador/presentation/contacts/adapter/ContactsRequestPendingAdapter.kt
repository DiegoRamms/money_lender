package com.appgame.prestador.presentation.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appgame.prestador.databinding.ItemRequestPendingContactBinding
import com.appgame.prestador.model.contact.Contact
import com.appgame.prestador.utils.clickWithDelay

class ContactsRequestPendingAdapter :
    ListAdapter<Contact, ContactsRequestPendingAdapter.ViewHolder>(ContactDiffCallback) {

    private var onItemClickListener: ((Contact) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRequestPendingContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = getItem(position)
        holder.name.text = contact.name
        holder.code.text = contact.code
    }


    inner class ViewHolder(val view: ItemRequestPendingContactBinding) :
        RecyclerView.ViewHolder(view.root) {
        val name: TextView = view.tvName
        val code: TextView = view.tvCode
        private val imageDelete: ImageView = view.imgDelete

        init {
            imageDelete.clickWithDelay {
                onItemClickListener?.let { it(getItem(adapterPosition)) }
            }
        }
    }

    fun setOnItemClickListener( listener:(Contact) -> Unit){
        onItemClickListener = listener
    }
}


