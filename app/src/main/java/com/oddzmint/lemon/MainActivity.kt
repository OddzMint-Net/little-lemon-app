/*
 * Copyright (c) 2026 OddzMint.
 * Licensed under the MIT License - see LICENSE file in the project root.
 */
package com.oddzmint.lemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScrollModifierNode
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.oddzmint.lemon.data.local.AppDatabase
import com.oddzmint.lemon.data.remote.network.MenuApi
import com.oddzmint.lemon.data.remote.network.httpClient
import com.oddzmint.lemon.data.repository.MenuRepositoryImpl
import com.oddzmint.lemon.presentation.ui.components.HomeRoute
import com.oddzmint.lemon.presentation.ui.screens.HomeScreen
import com.oddzmint.lemon.presentation.ui.theme.LemonTheme
import com.oddzmint.lemon.presentation.viewmodel.MenuViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    HomeRoute(
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}