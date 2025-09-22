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
import com.translator.domain.models.HistoryItem
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
        loadHistory()
    }

    private fun loadHistory() {
        translationViewModel.loadHistory()
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
        val adapter = HistoryAdapter { historyItem ->
            translationViewModel.removeHistoryItem(historyItem)
        }
        with (binding) {
            historyRecycler.layoutManager = LinearLayoutManager(activity)
            historyRecycler.adapter = adapter
            recyclerLayout.visibility = View.VISIBLE
            historyPlaceholder.visibility = View.GONE
        }

        translationViewModel.historyItems.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list.map { HistoryItem(it.id, it.contents) })
            if (list.isEmpty()) {
                setHistoryVisibility(false)
            } else {
                setHistoryVisibility(true)
            }
        }
        binding.clearHistoryButton.setOnClickListener {
            translationViewModel.clearHistory()
        }
    }

    private fun setHistoryVisibility(setVisible: Boolean) {
        with(binding) {
            if (setVisible){
                recyclerLayout.visibility = View.VISIBLE
                historyPlaceholder.visibility = View.GONE
            } else {
                recyclerLayout.visibility = View.GONE
                historyPlaceholder.visibility = View.VISIBLE
            }

        }
    }
}