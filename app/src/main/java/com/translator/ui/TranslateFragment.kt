package com.translator.ui

import android.content.Context
import com.translator.viewmodels.QueryItemsViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.translator.databinding.FragmentTranslateBinding
import com.translator.domain.models.QueryItem
import com.translator.ui.recycler.HistoryAdapter
import com.translator.viewmodels.TranslationViewModel
import dagger.hilt.android.AndroidEntryPoint


//В этом приложении я постарался обработать все возможные типы взаимодействия с пользователем.
//Добавление в избранное одного элемента истории ведёт к автоматическому отображению
//такого же состояния у таких же элементов истории. К примеру если один раз
//сохранить "hello" в избранном, то каждый раз при переводе этого слова оно будет отображаться
//как таковое. Постарался, чтобы все функции работали как надо, настроил корректное отображение
//элементов на экране. Ну а вообще мне приложение очень нравится. Оказывается API Skyeng
//иногда переводит лучше конкурентов. Вобщем задача была интересная :)
@AndroidEntryPoint
class TranslateFragment : Fragment() {
    private var _binding: FragmentTranslateBinding? = null
    private val binding get() = _binding!!
    private val translationViewModel: TranslationViewModel by activityViewModels()
    private val queryItemsViewModel: QueryItemsViewModel by activityViewModels()

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

    //Получение списка истории переводов
    private fun loadHistory() {
        queryItemsViewModel.loadHistory()
    }

    //Метод для настройки наблюдателей за результатом перевода и текстом ввода
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

    //Настройка взаимодействий с вводом текста и кнопкой перевода
    private fun setupInteractions() {
        binding.translationQuery.doOnTextChanged { text, _, _, _ ->
            translationViewModel.updateEditTextContents(text.toString())
        }

        translationViewModel.errorState.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(activity, "Отсутствует подключение к интернету", Toast.LENGTH_SHORT).show()
                translationViewModel.resetErrorState()
            }
        }

        //Осуществление перевода и вызов колбека для безопасной передачи в историю
        binding.transalteButton.setOnClickListener {
            translationViewModel.translate(translationViewModel.editTextContents.value.toString()) {
                queryItemsViewModel.addToHistory(
                    translationViewModel.editTextContents.value.orEmpty(),
                    it
                )
                hideKeyboard()
            }
        }
    }

    //Настройка списка истории переводов
    private fun setupHistoryRecycler() {
        val adapter = HistoryAdapter ({ historyItem ->
            queryItemsViewModel.removeHistoryItem(historyItem)
        },
            { historyItem ->
                queryItemsViewModel.onChangeFavorites(historyItem)
            })
        with (binding) {
            historyRecycler.layoutManager = LinearLayoutManager(activity)
            historyRecycler.adapter = adapter
        }

        queryItemsViewModel.historyItems.observe(viewLifecycleOwner) { list ->
            adapter.submitList(emptyList())
            adapter.submitList(list.map {
                QueryItem(
                    it.id,
                    it.originalWord,
                    it.translatedWord,
                    it.isFavorite
                )
            })
            if (list.isEmpty()) {
                setHistoryVisibility(false)
            } else {
                setHistoryVisibility(true)
            }
        }
        binding.clearHistoryButton.setOnClickListener {
            adapter.submitList(emptyList())
            queryItemsViewModel.clearHistory()
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

    fun Fragment.hideKeyboard() {
        val imm = requireActivity().getSystemService(
            Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}