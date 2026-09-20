package com.oddzmint.lemon.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oddzmint.lemon.presentation.ui.screens.HomeScreen
import com.oddzmint.lemon.presentation.viewmodel.MenuViewModel

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: MenuViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        onSortClick = viewModel::onOrderClick,
        onSearchPhraseChange = viewModel::onSearchPhraseChange,
        modifier = modifier
    )
}