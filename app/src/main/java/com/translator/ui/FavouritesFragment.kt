package com.translator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.translator.databinding.FragmentFavouritesBinding
import com.translator.domain.models.QueryItem
import com.translator.ui.recycler.FavoritesAdapter
import com.translator.viewmodels.QueryItemsViewModel


class FavouritesFragment : Fragment() {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private val queryItemsViewModel: QueryItemsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFavoritesRecycler()
        loadFavorites()
    }

    private fun loadFavorites() {
        queryItemsViewModel.loadFavorites()
    }

    private fun setupFavoritesRecycler() {
        val adapter = FavoritesAdapter { favoriteItem ->
            queryItemsViewModel.onChangeFavorites(favoriteItem)
        }

        binding.favoritesRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.favoritesRecycler.adapter = adapter

        queryItemsViewModel.favoritesItems.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list.map {
                QueryItem(
                    it.id,
                    it.originalWord,
                    it.translatedWord,
                    it.isFavorite
                )
            })
            if (list.isEmpty()) {
                setFavoritesVisibility(false)
            } else {
                setFavoritesVisibility(true)
            }
        }
        binding.clearFavoritesButton.setOnClickListener {
            queryItemsViewModel.uncheckFavorites()
            queryItemsViewModel.clearFavorites()
        }
    }

    private fun setFavoritesVisibility(setVisible: Boolean) {
        with(binding) {
            if (setVisible){
                recyclerLayout.visibility = View.VISIBLE
                favoritesPlaceholder.visibility = View.GONE
            } else {
                recyclerLayout.visibility = View.GONE
                favoritesPlaceholder.visibility = View.VISIBLE
            }

        }
    }
}
