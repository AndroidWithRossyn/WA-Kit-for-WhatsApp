package com.whatscan.whatsweb.whatzweb.whatwebscan.deleted.ui.chat.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.whatscan.whatsweb.whatzweb.whatwebscan.R
import com.whatscan.whatsweb.whatzweb.whatwebscan.deleted.data.model.Chat
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ItemMessageBinding
import com.whatscan.whatsweb.whatzweb.whatwebscan.deleted.utility.getTimeAndDate

class ChatDetailAdapter :
    ListAdapter<Chat, ChatDetailAdapter.ViewHolder>(ChatDetailDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //Used to store the clicked data
        private var currentData: Chat? = null

        @SuppressLint("SetTextI18n")
        fun bind(item: Chat?) {
            item?.let {
                if (it.isDeleted) binding.root.setBackgroundResource(R.drawable.deleted_message_background)
                binding.message.text = it.message
                binding.date.text = it.time.getTimeAndDate()
            }
            currentData = item
        }
    }

    class ChatDetailDiffCallback : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem.user == newItem.user
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem == newItem
        }
    }

}