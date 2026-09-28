package com.example.bookshelf.model

data class BookDetailsResponse(
    val id: String? = null,
    val volumeInfo: VolumeInfo? = null
)

data class VolumeInfo(
    val title: String? = null,
    val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    val thumbnail: String? = null
)