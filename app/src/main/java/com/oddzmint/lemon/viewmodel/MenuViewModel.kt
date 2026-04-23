package com.oddzmint.lemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oddzmint.lemon.data.local.MenuItemUi
import com.oddzmint.lemon.data.remote.dto.MenuItem
import com.oddzmint.lemon.data.repository.MenuRepository
import com.oddzmint.lemon.ui.mapper.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MenuViewModel(
    private val repository: MenuRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    private val _allMenuItems = MutableStateFlow<List<MenuItemUi>>(emptyList())
    private val _menuItems = MutableStateFlow<List<MenuItemUi>>(emptyList())
    val menuItems: StateFlow<List<MenuItemUi>> = _menuItems.asStateFlow()

    private val _isOrdered = MutableStateFlow(false)
    val isOrdered: StateFlow<Boolean> = _isOrdered.asStateFlow()

    private val _searchPhrase = MutableStateFlow("")
    val searchPhrase: StateFlow<String> = _searchPhrase.asStateFlow()

    init {
        observeMenu()
        refreshMenu()
    }

    private fun observeMenu(){
        viewModelScope.launch {
            repository.observeMenu().collect { items ->
                _allMenuItems.value = items.map { it.toUi() }
                applyFilters()
            }
        }
    }

    private fun refreshMenu() {
        viewModelScope.launch {
            _isLoading.value = true
            runCatching {
                repository.refreshMenu()
            }
            _isLoading.value = false
        }
    }

    fun onOrderClick() {
        _isOrdered.value = !_isOrdered.value
        applyFilters()
    }

    fun onSearchPhraseChange(value: String) {
        _searchPhrase.value = value
        applyFilters()
    }

    private fun applyFilters() {
        val orderedItems = if (_isOrdered.value) {
            _allMenuItems.value.sortedBy { it.title }
        } else {
            _allMenuItems.value
        }
        _menuItems.value = if (_searchPhrase.value.isNotBlank()) {
            orderedItems.filter {
                it.title.contains(_searchPhrase.value, ignoreCase = true)
            }
        } else {
            orderedItems
        }
    }
}