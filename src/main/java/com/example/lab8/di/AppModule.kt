package com.example.lab8.di

import android.content.Context
import androidx.room.Room
import com.example.lab8.data.local.Lab8Database
import com.example.lab8.data.local.dao.PhotoDao
import com.example.lab8.data.local.dao.RecentQueryDao
import com.example.lab8.data.local.dao.RemoteKeysDao
import com.example.lab8.data.remote.Lab8Api
import com.example.lab8.util.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Lab8Database {
        return Room.databaseBuilder(
            context,
            Lab8Database::class.java,
            "lab8_database"
        )
            .fallbackToDestructiveMigration() // 👈 esta línea es clave
            .build()
    }

    @Provides
    fun providePhotoDao(db: Lab8Database): PhotoDao = db.photoDao()

    @Provides
    fun provideRecentQueryDao(db: Lab8Database): RecentQueryDao = db.recentQueryDao()

    @Provides
    fun provideRemoteKeysDao(db: Lab8Database): RemoteKeysDao = db.remoteKeysDao()

    @Provides
    @Singleton
    fun provideApi(): Lab8Api = RetrofitInstance.api
}
