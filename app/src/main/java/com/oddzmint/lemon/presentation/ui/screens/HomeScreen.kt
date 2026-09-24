/*
 * Copyright (c) 2026 OddzMint.
 * Licensed under the MIT License - see LICENSE file in the project root.
 */
package com.oddzmint.lemon.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oddzmint.lemon.R
import com.oddzmint.lemon.presentation.ui.model.MenuItemUi
import com.oddzmint.lemon.presentation.ui.HomeUiState
import com.oddzmint.lemon.presentation.ui.MenuContent
import com.oddzmint.lemon.presentation.ui.components.ErrorContent
import com.oddzmint.lemon.presentation.ui.components.LemonHeader
import com.oddzmint.lemon.presentation.ui.components.LemonSearchBar
import com.oddzmint.lemon.presentation.ui.components.LoadingContent
import com.oddzmint.lemon.presentation.ui.components.MenuList
import com.oddzmint.lemon.presentation.ui.components.OrderButton
import com.oddzmint.lemon.presentation.ui.theme.LemonTheme

@Composable
fun HomeScreen(
    state: HomeUiState,
    onSortClick: () -> Unit,
    onSearchPhraseChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxSize())
    {
        LemonHeader()
        OrderButton(
            isOrdered = state.isSorted,
            onSortClick
        )

        LemonSearchBar(
            value = state.searchPhrase,
            onValueChange = onSearchPhraseChange,
            label = { Text(stringResource(R.string.search_hint)) },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        when (val content = state.content) {
            MenuContent.Loading -> LoadingContent()
            MenuContent.Error -> ErrorContent()
            is MenuContent.Success -> MenuList(items = content.items)
        }
    }
}

private val previewItems = listOf(
    MenuItemUi(1, "Greek", "10", "Freshly prepared and served with Little Lemon style.", R.drawable.greek),
    MenuItemUi(2, "Caesar", "12", "Freshly prepared and served with Little Lemon style.", R.drawable.caesar),
    MenuItemUi(3, "Hummus", "8", "Freshly prepared and served with Little Lemon style.", R.drawable.hummus)
)

@Preview(showBackground = true, name = "Success")
@Composable
private fun HomeScreenSuccessPreview() {
    LemonTheme {
        HomeScreen(
            state = HomeUiState(content = MenuContent.Success(previewItems)),
            onSortClick = {},
            onSearchPhraseChange = {}
        )
    }
}

@Preview(showBackground = true, name = "Loading",device = "spec:width=1080px,height=1920px,dpi=440")
@Composable
private fun HomeScreenLoadingPreview() {
    LemonTheme {
        HomeScreen(
            state = HomeUiState(content = MenuContent.Loading),
            onSortClick = {},
            onSearchPhraseChange = {}
        )
    }
}

@Preview(showBackground = true, name = "Error",device = "spec:width=1080px,height=1920px,dpi=440")
@Composable
private fun HomeScreenErrorPreview() {
    LemonTheme {
        HomeScreen(
            state = HomeUiState(content = MenuContent.Loading),
            onSortClick = {},
            onSearchPhraseChange = {}
        )
    }
}

@Preview(showBackground = true, name = "Ordered",device = "spec:width=1080px,height=1920px,dpi=440")
@Composable
private fun HomeScreenOrderedPreview() {
    LemonTheme {
        HomeScreen(
            state = HomeUiState(
                isSorted = true,
                content = MenuContent.Success(previewItems.sortedBy { it.title })
            ),
            onSortClick = {},
            onSearchPhraseChange = {}
        )
    }
}

@Preview(showBackground = true, name = "Sorted + searching",device = "spec:width=1080px,height=1920px,dpi=440")
@Composable
private fun HomeScreenSearchPreview() {
    LemonTheme {
        HomeScreen(
            state = HomeUiState(
                searchPhrase = "h",
                isSorted = true,
                content = MenuContent.Success(previewItems.filter { it.title.contains("h", ignoreCase = true) })
            ),
            onSortClick = {},
            onSearchPhraseChange = {}
        )
    }
}