package com.example.lab8.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey val photoId: String,
    val prevKey: Int?,
    val nextKey: Int?
)
