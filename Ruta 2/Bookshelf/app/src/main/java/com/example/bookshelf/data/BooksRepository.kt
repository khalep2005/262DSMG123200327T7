package com.example.bookshelf.data

import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BooksApiService

interface BooksRepository {
    suspend fun getBooks(query: String): List<Book>
}

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {
    override suspend fun getBooks(query: String): List<Book> {
        val searchResponse = booksApiService.searchBooks(query)
        val bookIds = searchResponse.items?.mapNotNull { it.id } ?: emptyList()

        val books = mutableListOf<Book>()
        for (id in bookIds) {
            val bookDetails = booksApiService.getBookDetails(id)
            val title = bookDetails.volumeInfo?.title ?: "Unknown Title"
            var thumbnail = bookDetails.volumeInfo?.imageLinks?.thumbnail ?: ""
            thumbnail = thumbnail.replace("http://", "https://")
            
            if (thumbnail.isNotEmpty()) {
               books.add(Book(id, title, thumbnail))
            }
        }
        return books
    }
}