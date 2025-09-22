package com.translator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.translator.databinding.FragmentTranslateBinding
import com.translator.ui.recycler.HistoryAdapter
import com.translator.viewmodels.TranslationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TranslateFragment : Fragment() {
    private var _binding: FragmentTranslateBinding? = null
    private val binding get() = _binding!!
    private val translationViewModel: TranslationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTranslateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupInteractions()
        setupHistoryRecycler()
    }

    private fun setupObservers() {
        translationViewModel.translationResult.observe(viewLifecycleOwner) {
            binding.translationResultLayout.visibility = View.VISIBLE
            binding.translationResult.text = it
        }

        translationViewModel.editTextContents.observe(viewLifecycleOwner) {
            with(binding) {
                if (it.isEmpty()) {
                    translationResultLayout.visibility = View.GONE
                }
            }
        }
    }

    private fun setupInteractions() {
        binding.translationQuery.doOnTextChanged { text, _, _, _ ->
            translationViewModel.updateEditTextContents(text.toString())
        }

        binding.transalteButton.setOnClickListener {
            translationViewModel.translate(translationViewModel.editTextContents.value.toString())
        }
    }

    private fun setupHistoryRecycler() {
        val adapter = HistoryAdapter()
        with (binding) {
            historyRecycler.layoutManager = LinearLayoutManager(activity)
            historyRecycler.adapter = adapter
            recyclerLayout.visibility = View.VISIBLE
            historyPlaceholder.visibility = View.GONE
        }

        translationViewModel.historyItems.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            if (list.isEmpty()) {
                with(binding) {
                    recyclerLayout.visibility = View.GONE
                    historyPlaceholder.visibility = View.VISIBLE
                }
            } else {
                with(binding) {
                    recyclerLayout.visibility = View.VISIBLE
                    historyPlaceholder.visibility = View.GONE
                }
            }
        }
        binding.clearHistoryButton.setOnClickListener {
            translationViewModel.clearHistory()
        }
    }
}