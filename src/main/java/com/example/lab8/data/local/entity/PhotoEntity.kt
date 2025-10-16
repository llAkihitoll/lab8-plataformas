package com.example.lab8.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey val id: String,
    val width: Int,
    val height: Int,
    val thumbnailUrl: String,
    val fullUrl: String,
    val author: String,
    val isFavorite: Boolean = false,
    val queryKey: String,
    val pageIndex: Int,
    val updatedAt: Long = System.currentTimeMillis(),
    val url: String,
    val description: String,
    val page: Int
)

