package com.example.lab8.data.remote

import com.example.lab8.data.remote.dto.PhotoDto
import com.example.lab8.data.remote.dto.PhotoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Lab8Api {

    @GET("search")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): PhotoSearchResponse

    @GET("photos/{id}")
    suspend fun getPhotoById(@Path("id") id: String): PhotoDto
}
