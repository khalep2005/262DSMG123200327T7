package com.example.bookshelf.network

import com.example.bookshelf.model.BookDetailsResponse
import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.SearchResponse
import com.example.bookshelf.model.VolumeInfo

class FakeBooksApiService : BooksApiService {
    override suspend fun searchBooks(query: String): SearchResponse {
        return SearchResponse(
            items = listOf(BookItem("id1"), BookItem("id2"))
        )
    }

    override suspend fun getBookDetails(volumeId: String): BookDetailsResponse {
        return if (volumeId == "id1") {
            BookDetailsResponse(
                id = "id1",
                volumeInfo = VolumeInfo(
                    title = "Fake Title 1",
                    imageLinks = ImageLinks(thumbnail = "http://fake.url/1.jpg")
                )
            )
        } else {
            BookDetailsResponse(
                id = "id2",
                volumeInfo = VolumeInfo(
                    title = "Fake Title 2",
                    imageLinks = ImageLinks(thumbnail = "http://fake.url/2.jpg")
                )
            )
        }
    }
}