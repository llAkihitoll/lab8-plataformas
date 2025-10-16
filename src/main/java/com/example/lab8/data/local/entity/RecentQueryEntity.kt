package com.example.lab8.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_queries")
data class RecentQueryEntity(
    @PrimaryKey val query: String,
    val lastUsedAt: Long = System.currentTimeMillis()
)
