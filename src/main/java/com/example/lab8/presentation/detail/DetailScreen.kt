package com.example.lab8.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lab8.data.local.entity.PhotoEntity
import com.example.lab8.domain.Lab8Repository
import kotlinx.coroutines.launch
import com.example.lab8.utils.NetworkUtils
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder




@Composable
fun DetailScreen(
    photoId: String,
    repo: Lab8Repository,
    context: Context
) {
    var photo by remember { mutableStateOf<PhotoEntity?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(photoId) {
        val local = repo.getPhotoById(photoId)
        if (local != null) {
            photo = local
        } else if (NetworkUtils.isNetworkAvailable(context)) {
            val fromApi = repo.fetchPhotoFromApi(photoId)
            fromApi?.let {
                repo.insertPhoto(it)
                photo = it
            }
        }
    }

    photo?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(model = it.fullUrl, contentDescription = null)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Autor: ${it.author}")
            Text("Dimensiones: ${it.width}x${it.height}")
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                IconButton(onClick = {
                    coroutineScope.launch {
                        repo.toggleFavorite(it.id, !it.isFavorite)
                        photo = it.copy(isFavorite = !it.isFavorite)
                    }
                }) {
                    Icon(
                        imageVector = if (it.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorito"
                    )
                }

                Button(onClick = {
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, it.fullUrl)
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(intent, "Compartir"))
                }) {
                    Text("Compartir")
                }
            }
        }
    } ?: Text("Cargando...")
}
