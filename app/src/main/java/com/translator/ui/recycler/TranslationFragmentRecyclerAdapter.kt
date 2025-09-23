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

class HistoryAdapter(private val onDeletePressed: (HistoryItem) -> Unit,
                     private val onAddToFavorites: (HistoryItem, Boolean) -> Boolean) :
    ListAdapter<HistoryItem, HistoryAdapter.HistoryViewHolder>(HistoryDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_history, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val historyOriginalWord: TextView by lazy { itemView.findViewById(R.id.recycler_history_original_word) }
        private val historyTranslatedWord: TextView by lazy { itemView.findViewById(R.id.recycler_history_translated_word) }
        private val clearBtn: ImageView by lazy { itemView.findViewById(R.id.recycler_delete_button) }
        private val toFavoritesBtn: ImageView by lazy { itemView.findViewById(R.id.translation_to_favorites_button) }
        private var isFavorite: Boolean = false

        fun bind(item: HistoryItem) {
            historyOriginalWord.text = item.originalWord
            historyTranslatedWord.text = item.translatedWord
            clearBtn.setOnClickListener {
                onDeletePressed(item)
            }
            toFavoritesBtn.setOnClickListener {
                isFavorite = onAddToFavorites(item, isFavorite)
                if (isFavorite) {
                    toFavoritesBtn.setImageResource(R.drawable.ic_bookmark_filled)
                } else {
                    toFavoritesBtn.setImageResource(R.drawable.ic_bookmark_outlined)
                }
            }
        }
    }
}
