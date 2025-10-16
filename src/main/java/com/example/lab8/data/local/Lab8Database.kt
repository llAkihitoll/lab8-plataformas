package com.example.lab8.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab8.data.local.dao.PhotoDao
import com.example.lab8.data.local.dao.RecentQueryDao
import com.example.lab8.data.local.dao.RemoteKeysDao
import com.example.lab8.data.local.entity.PhotoEntity
import com.example.lab8.data.local.entity.RecentQueryEntity
import com.example.lab8.data.local.entity.RemoteKeys

@Database(
    entities = [PhotoEntity::class, RecentQueryEntity::class, RemoteKeys::class],
    version = 2,
    exportSchema = false
)
abstract class Lab8Database : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
    abstract fun recentQueryDao(): RecentQueryDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}
