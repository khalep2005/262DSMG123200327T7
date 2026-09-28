package com.example.bookshelf.network

import com.example.bookshelf.model.BookDetailsResponse
import com.example.bookshelf.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    suspend fun searchBooks(@Query("q") query: String): SearchResponse

    @GET("volumes/{volume_id}")
    suspend fun getBookDetails(@Path("volume_id") volumeId: String): BookDetailsResponse
}