package com.example.lab8.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lab8.data.local.entity.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM remote_keys WHERE photoId = :id")
    suspend fun getRemoteKeyByPhotoId(id: String): RemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<RemoteKeys>)

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}
