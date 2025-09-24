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

object FavoritesDiffCallback : DiffUtil.ItemCallback<QueryItem>() {
    override fun areItemsTheSame(oldItem: QueryItem, newItem: QueryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: QueryItem, newItem: QueryItem): Boolean {
        return oldItem == newItem
    }
}

class FavoritesAdapter(private val onAddToFavorites: (QueryItem, Int) -> Unit) :
    ListAdapter<QueryItem, FavoritesAdapter.FavoritesViewHolder>(FavoritesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_history, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val historyOriginalWord: TextView by lazy { itemView.findViewById(R.id.recycler_history_original_word) }
        private val historyTranslatedWord: TextView by lazy { itemView.findViewById(R.id.recycler_history_translated_word) }
        private val toFavoritesBtn: ImageView by lazy { itemView.findViewById(R.id.translation_to_favorites_button) }

        fun bind(item: QueryItem, position: Int) {
            historyOriginalWord.text = item.originalWord
            historyTranslatedWord.text = item.translatedWord

            toFavoritesBtn.setOnClickListener {
                onAddToFavorites(item, position)
            }

            toFavoritesBtn.setImageResource(
                if (item.isFavorite) R.drawable.ic_bookmark_filled
                else R.drawable.ic_bookmark_outlined
            )

        }
    }
}
