package com.oddzmint.lemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.oddzmint.lemon.data.local.AppDatabase
import com.oddzmint.lemon.data.local.MenuDao
import com.oddzmint.lemon.data.remote.network.MenuApi
import com.oddzmint.lemon.data.remote.network.httpClient
import com.oddzmint.lemon.data.repository.MenuRepository
import com.oddzmint.lemon.ui.screens.HomeScreen
import com.oddzmint.lemon.ui.theme.LemonTheme
import com.oddzmint.lemon.viewmodel.MenuViewModel

class MainActivity : ComponentActivity() {
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "little_lemon_database"
        ).build()
    }

    private val viewModel by lazy {
        MenuViewModel(
            repository = MenuRepository(
                menuApi = MenuApi(httpClient),
                menuDao = database.menuDao()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    HomeScreen(
                        viewModel = viewModel
                    )
                }

            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        LemonTheme {
            HomeScreen(
                viewModel = MenuViewModel(
                    repository = MenuRepository(
                        menuApi = MenuApi(httpClient),
                        menuDao = database.menuDao()
                    )
                )
            )
        }
    }
}