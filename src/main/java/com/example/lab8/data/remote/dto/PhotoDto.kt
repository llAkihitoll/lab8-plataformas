package com.example.lab8.data.remote.dto

data class PhotoDto(
    val id: String,
    val width: Int,
    val height: Int,
    val urls: UrlsDto,
    val user: UserDto,
    val description: String?
)

data class UrlsDto(
    val thumb: String,
    val full: String,
    val small: String
)

data class UserDto(
    val name: String
)


