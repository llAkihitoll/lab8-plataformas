package com.example.lab8.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lab8.data.local.entity.RecentQueryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentQueryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuery(query: RecentQueryEntity)

    @Query("SELECT * FROM recent_queries ORDER BY lastUsedAt DESC LIMIT 10")
    fun getRecentQueries(): Flow<List<RecentQueryEntity>>
}
