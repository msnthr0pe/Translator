package com.translator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.translator.data.remote.SkyengApi
import com.translator.data.repository.TranslationRepositoryImpl
import com.translator.databinding.FragmentTranslateBinding
import com.translator.domain.models.TranslatedWord
import com.translator.domain.models.TranslationRequest
import com.translator.domain.usecases.TranslateUseCase
import com.translator.viewmodels.NavigationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TranslateFragment : Fragment() {
    @Inject lateinit var skyengApi: SkyengApi

    private var _binding: FragmentTranslateBinding? = null
    private val binding get() = _binding!!
    private val navViewModel: NavigationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTranslateBinding.inflate(inflater, container, false)

        val translateUseCase = TranslateUseCase(TranslationRepositoryImpl(skyengApi))

        lifecycleScope.launch {
            val response: TranslatedWord = translateUseCase(TranslationRequest("hello"))
            Log.d("TranslatorLog", response.result)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navViewModel.navigateToFavourites.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_translate_to_favorites)
                navViewModel.doneNavigatingToTranslate()
            }
        }

        with(binding) {
            bottomNav.menu.clear()
            bottomNav.inflateMenu(R.menu.menu_bottom_nav)
            bottomNav.menu.findItem(R.id.nav_translate).isChecked = true
            bottomNav.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_translate -> true
                    R.id.nav_favourites -> {
                        navViewModel.goToFavorites()
                        true
                    }
                    else -> false
                }
            }
        }
    }
}