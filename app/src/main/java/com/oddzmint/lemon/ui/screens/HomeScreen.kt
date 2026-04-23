package com.oddzmint.lemon.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.oddzmint.lemon.R
import com.oddzmint.lemon.ui.components.LemonHeader
import com.oddzmint.lemon.ui.components.LemonSearchBar
import com.oddzmint.lemon.ui.components.MenuItemCard
import com.oddzmint.lemon.ui.components.OrderButton
import com.oddzmint.lemon.viewmodel.MenuViewModel

@Composable
fun HomeScreen(
    viewModel: MenuViewModel
) {
    val menuItems by viewModel.menuItems.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val orderMenuItems by viewModel.isOrdered.collectAsState()
    val searchPhrase by viewModel.searchPhrase.collectAsState()
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier.fillMaxSize()
    )

    {
        LemonHeader()
        OrderButton(
            isOrdered = orderMenuItems,
            onClick = viewModel::onOrderClick
        )
        LemonSearchBar(
            value = searchPhrase,
            onValueChange = viewModel::onSearchPhraseChange,
            label = { Text(stringResource(R.string.search_hint)) },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )


        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            )
            {
                items(
                    items = menuItems,
                    key = { it.id }
                ) { item ->
                    MenuItemCard(item)
                }
            }
        }
    }
}