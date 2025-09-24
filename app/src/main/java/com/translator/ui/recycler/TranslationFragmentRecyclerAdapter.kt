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
import com.translator.domain.models.QueryItem

object HistoryDiffCallback : DiffUtil.ItemCallback<QueryItem>() {
    override fun areItemsTheSame(oldItem: QueryItem, newItem: QueryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: QueryItem, newItem: QueryItem): Boolean {
        return oldItem == newItem
    }
}

class HistoryAdapter(private val onDeletePressed: (QueryItem) -> Unit,
                     private val onChangeFavorites: (QueryItem) -> Unit) :
    ListAdapter<QueryItem, HistoryAdapter.HistoryViewHolder>(HistoryDiffCallback) {

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

        fun bind(item: QueryItem) {
            historyOriginalWord.text = item.originalWord
            historyTranslatedWord.text = item.translatedWord
            clearBtn.setOnClickListener {
                onDeletePressed(item)
            }

            toFavoritesBtn.setOnClickListener {
                onChangeFavorites(item)
            }

            toFavoritesBtn.setImageResource(
                if (item.isFavorite) R.drawable.ic_bookmark_filled
                else R.drawable.ic_bookmark_outlined
            )

        }
    }
}
