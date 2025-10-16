package com.example.lab8.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab8.domain.Lab8Repository
import com.example.lab8.utils.NetworkUtils
import com.example.lab8.data.local.entity.PhotoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.paging.cachedIn

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class) 
@HiltViewModel
class Lab8ViewModel @Inject constructor(
    private val repo: Lab8Repository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _currentQuery = MutableStateFlow("")
    val currentQuery: StateFlow<String> = _currentQuery

    // Búsquedas recientes
    val recentQueries = repo.getRecentQueries()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    // Favoritos
    val favorites = repo.getFavorites()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    // Paginación dinámica según disponibilidad de red
    val pagedPhotos = _currentQuery
        .flatMapLatest { query ->
            if (NetworkUtils.isNetworkAvailable(context)) {
                repo.searchPhotos(query)
            } else {
                repo.offlinePhotos(query)
            }
        }
        .cachedIn(viewModelScope)

    fun search(query: String) {
        _currentQuery.value = query
        viewModelScope.launch {
            repo.saveRecentQuery(query)
        }
    }

    fun toggleFavorite(photo: PhotoEntity) {
        viewModelScope.launch {
            repo.toggleFavorite(photo.id, !photo.isFavorite)
        }
    }
}
