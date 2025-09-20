package com.translator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.translator.databinding.FragmentFavouritesBinding
import com.translator.viewmodels.NavigationViewModel
import kotlin.getValue

class FavouritesFragment : Fragment() {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private val navViewModel: NavigationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navViewModel.navigateToTranslate.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_favorites_to_translate)
                navViewModel.doneNavigatingToFavorites()
            }
        }

        with(binding) {
            bottomNav.menu.clear()
            bottomNav.inflateMenu(R.menu.menu_bottom_nav)
            bottomNav.menu.findItem(R.id.nav_favourites).isChecked = true
            bottomNav.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_translate -> {
                        navViewModel.goToTranslate()
                        true
                    }
                    R.id.nav_favourites -> true
                    else -> false
                }
            }
        }
    }
}