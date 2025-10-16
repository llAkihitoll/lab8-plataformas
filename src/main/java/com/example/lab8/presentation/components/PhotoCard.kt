package com.example.lab8.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lab8.data.local.entity.PhotoEntity

@Composable
fun PhotoCard(
    photo: PhotoEntity,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { /* Navegar o mostrar detalle */ },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = photo.url,
                contentDescription = photo.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = photo.author,
                    style = MaterialTheme.typography.bodyLarge
                )

                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites"
                    )
                }
            }
        }
    }
}
