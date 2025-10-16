package com.example.lab8.data.mapper

import com.example.lab8.data.local.entity.PhotoEntity
import com.example.lab8.data.remote.dto.PhotoDto

fun PhotoDto.toEntity(queryKey: String, page: Int): PhotoEntity {
    return PhotoEntity(
        id = id,
        width = width,
        height = height,
        thumbnailUrl = urls.thumb,
        fullUrl = urls.full,
        author = user.name,
        isFavorite = false,
        queryKey = queryKey,
        pageIndex = page,
        updatedAt = System.currentTimeMillis(),
        url = urls.small,
        description = description ?: "",
        page = page
    )
}
