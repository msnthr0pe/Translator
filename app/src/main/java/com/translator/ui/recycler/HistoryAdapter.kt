package com.translator.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.translator.R
import com.translator.domain.models.HistoryItem

object HistoryDiffCallback : DiffUtil.ItemCallback<HistoryItem>() {
    override fun areItemsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
        return oldItem == newItem
    }
}

class HistoryAdapter(private val onDeletePressed: (HistoryItem) -> Unit) :
    ListAdapter<HistoryItem, HistoryAdapter.HistoryViewHolder>(HistoryDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_translation, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val historyText: TextView by lazy { itemView.findViewById(R.id.recycler_translation) }
        private val clearBtn: ImageView by lazy { itemView.findViewById(R.id.recycler_delete_button) }

        fun bind(item: HistoryItem) {
            historyText.text = item.contents
            clearBtn.setOnClickListener {
                onDeletePressed(item)
            }
        }
    }
}
