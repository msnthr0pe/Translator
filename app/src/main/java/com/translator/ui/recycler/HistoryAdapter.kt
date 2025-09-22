package com.translator.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.translator.R
import com.translator.models.HistoryItem
import kotlin.getValue

class HistoryAdapter(val historyItems: List<HistoryItem>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HistoryViewHolder {
        return HistoryViewHolder(
            LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: HistoryViewHolder,
        position: Int,
    ) {
        holder.bind(historyItems[position])
    }

    override fun getItemCount(): Int {
        return historyItems.size
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val historyItem: TextView by lazy {
            itemView.findViewById(R.id.recycler_translation)
        }
        fun bind(historyItem: HistoryItem) {
            this.historyItem.text = historyItem.historyItem

        }
    }
}