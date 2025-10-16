package com.example.lab8.domain

import androidx.paging.*
import com.example.lab8.data.local.Lab8Database
import com.example.lab8.data.local.entity.PhotoEntity
import com.example.lab8.data.local.entity.RecentQueryEntity
import com.example.lab8.data.mediator.Lab8RemoteMediator
import com.example.lab8.data.remote.Lab8Api
import com.example.lab8.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class Lab8Repository(
    private val db: Lab8Database,
    private val api: Lab8Api
) {

    fun searchPhotos(query: String): Flow<PagingData<PhotoEntity>> {
        val pagingSourceFactory = { db.photoDao().pagingSource(query) }

        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = Lab8RemoteMediator(query, api, db),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun offlinePhotos(query: String): Flow<PagingData<PhotoEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { db.photoDao().pagingSource(query) }
        ).flow
    }

    suspend fun saveRecentQuery(query: String) {
        db.recentQueryDao().insertQuery(
            RecentQueryEntity(query = query, lastUsedAt = System.currentTimeMillis())
        )
    }

    fun getRecentQueries() = db.recentQueryDao().getRecentQueries()

    fun getFavorites(): Flow<List<PhotoEntity>> = db.photoDao().getFavorites()

    suspend fun toggleFavorite(id: String, isFavorite: Boolean) {
        db.photoDao().toggleFavorite(id, isFavorite)
    }

    suspend fun getPhotoById(id: String): PhotoEntity? {
        return db.photoDao().getPhotoById(id)
    }

    suspend fun fetchPhotoFromApi(id: String): PhotoEntity? {
        return try {
            val dto = api.getPhotoById(id)
            dto.toEntity(queryKey = "", page = 0)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun insertPhoto(photo: PhotoEntity) {
        db.photoDao().insertAll(listOf(photo))
    }
}
