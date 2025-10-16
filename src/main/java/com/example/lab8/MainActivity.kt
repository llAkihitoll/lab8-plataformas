package com.example.lab8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lab8.presentation.Lab8ViewModel
import com.example.lab8.presentation.home.HomeScreen
import com.example.lab8.ui.theme.Lab8Theme
import dagger.hilt.android.AndroidEntryPoint
import androidx.paging.compose.collectAsLazyPagingItems


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab8App()
        }
    }
}

@Composable
fun Lab8App() {
    val viewModel: Lab8ViewModel = hiltViewModel()
    val photos = viewModel.pagedPhotos.collectAsLazyPagingItems()
    val favorites = viewModel.favorites.collectAsState().value

    Lab8Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            HomeScreen(
                photos = photos,
                onSearch = viewModel::search,
                favorites = favorites,
                onToggleFavorite = viewModel::toggleFavorite
            )
        }
    }
}
