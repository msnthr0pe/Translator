package com.translator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import com.translator.databinding.FragmentTranslateBinding
import com.translator.viewmodels.TranslationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TranslateFragment : Fragment() {
    private var _binding: FragmentTranslateBinding? = null
    private val binding get() = _binding!!
    private val translateViewModel: TranslationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTranslateBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        translateViewModel.translationResult.observe(viewLifecycleOwner) {
            binding.translationResultLayout.visibility = View.VISIBLE
            binding.translationResult.text = it
        }

        translateViewModel.editTextContents.observe(viewLifecycleOwner) {
            with(binding) {
                if (it.isEmpty()) {
                    translationResultLayout.visibility = View.GONE
                }
            }
        }

        binding.translationQuery.doOnTextChanged { text, _, _, _ ->
            translateViewModel.updateEditTextContents(text.toString())
        }

        binding.transalteButton.setOnClickListener {
            translateViewModel.translate(translateViewModel.editTextContents.value.toString())
        }
    }
}