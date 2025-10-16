package com.example.lab8.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lab8.data.local.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Query("DELETE FROM photos")
    suspend fun clearAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<PhotoEntity>)

    @Query("SELECT * FROM photos WHERE queryKey = :queryKey ORDER BY pageIndex ASC")
    fun pagingSource(queryKey: String): PagingSource<Int, PhotoEntity>

    @Query("SELECT * FROM photos WHERE id = :id")
    suspend fun getPhotoById(id: String): PhotoEntity?

    @Query("DELETE FROM photos WHERE queryKey = :queryKey")
    suspend fun clearByQuery(queryKey: String)

    @Query("UPDATE photos SET isFavorite = :favorite WHERE id = :id")
    suspend fun toggleFavorite(id: String, favorite: Boolean)

    @Query("SELECT * FROM photos WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<PhotoEntity>>
}
