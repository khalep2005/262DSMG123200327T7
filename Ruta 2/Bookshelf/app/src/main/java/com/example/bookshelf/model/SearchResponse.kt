package com.example.bookshelf.model

data class SearchResponse(
    val items: List<BookItem>? = null
)

data class BookItem(
    val id: String? = null,
    val volumeInfo: VolumeInfo? = null
)