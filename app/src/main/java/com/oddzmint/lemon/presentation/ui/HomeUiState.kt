package com.oddzmint.lemon.presentation.ui

import com.oddzmint.lemon.presentation.ui.model.MenuItemUi

data class HomeUiState(
    val searchPhrase: String = "",
    val isSorted: Boolean = false,
    val content: MenuContent = MenuContent.Loading
)

sealed interface MenuContent {
    data object Loading : MenuContent
    data object Error : MenuContent
    data class Success(val items: List<MenuItemUi>): MenuContent
}
