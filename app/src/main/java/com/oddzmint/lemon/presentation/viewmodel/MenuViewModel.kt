package com.oddzmint.lemon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oddzmint.lemon.domain.model.MenuItem
import com.oddzmint.lemon.domain.repository.MenuRepository
import com.oddzmint.lemon.presentation.ui.HomeUiState
import com.oddzmint.lemon.presentation.ui.MenuContent
import com.oddzmint.lemon.presentation.ui.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repository: MenuRepository
) : ViewModel() {

    // Inputs: the only things that ever change
    private val isSorted = MutableStateFlow(false)
    private val searchPhrase = MutableStateFlow("")
    private val isRefreshing = MutableStateFlow(true)
    private val refreshFailed = MutableStateFlow(false)

    // Output: rebuilt automatically whenever any input changes
    val uiState: StateFlow<HomeUiState> = combine(
        repository.observeMenu(),
        isSorted,
        searchPhrase,
        isRefreshing,
        refreshFailed
    ) { items, sorted, phrase, refreshing, failed ->

        val visibleItems = items
            .filter { phrase.isBlank() || it.title.contains(phrase, ignoreCase = true) }
            .let { if (sorted) it.sortedBy(MenuItem::title) else it }
            .map { it.toUi() }

        val content = when {
            items.isNotEmpty() -> MenuContent.Success(visibleItems)
            refreshing -> MenuContent.Loading
            failed -> MenuContent.Error
            else -> MenuContent.Success(emptyList())
        }

        HomeUiState(
            searchPhrase = phrase,
            isSorted = sorted,
            content = content
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState()
    )

    init {
        refreshMenu()
    }

    fun onOrderClick() = isSorted.update { !it }

    fun onSearchPhraseChange(value: String) {
        searchPhrase.value = value
    }

    private fun refreshMenu() {
        viewModelScope.launch {
            isRefreshing.value = true
            refreshFailed.value = false
            try {
                repository.refreshMenu()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                refreshFailed.value = true
            } finally {
                isRefreshing.value = false
            }
        }
    }
}