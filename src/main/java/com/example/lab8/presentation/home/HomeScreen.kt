package com.example.lab8.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.lab8.data.local.entity.PhotoEntity
import com.example.lab8.presentation.components.PhotoCard

@Composable
fun HomeScreen(
    photos: LazyPagingItems<PhotoEntity>,
    onSearch: (String) -> Unit,
    favorites: List<PhotoEntity>,
    onToggleFavorite: (PhotoEntity) -> Unit
) {
    var query by remember { mutableStateOf("") }

    Column {
        TextField(
            value = query,
            onValueChange = {
                query = it
                if (query.length > 2) onSearch(query)
            },
            label = { Text("Buscar fotos") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
            items(photos.itemSnapshotList.items) { photo ->
                photo?.let {
                    val isFavorite = favorites.any { fav -> fav.id == photo.id }
                    PhotoCard(photo = photo, isFavorite = isFavorite) {
                        onToggleFavorite(photo.copy(isFavorite = !isFavorite))
                    }
                }
            }
        }
    }
}
