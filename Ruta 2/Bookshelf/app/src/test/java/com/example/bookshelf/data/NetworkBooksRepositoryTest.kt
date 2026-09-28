package com.example.bookshelf.data

import com.example.bookshelf.network.FakeBooksApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkBooksRepositoryTest {
    @Test
    fun networkBooksRepository_getBooks_verifyBookList() = runTest {
        val repository = NetworkBooksRepository(
            booksApiService = FakeBooksApiService()
        )
        
        val books = repository.getBooks("query")
        
        // Verificamos que se devuelven 2 libros
        assertEquals(2, books.size)
        
        // Verificamos el contenido y el reemplazo de http por https
        assertEquals("id1", books[0].id)
        assertEquals("Fake Title 1", books[0].title)
        assertEquals("https://fake.url/1.jpg", books[0].imageUrl)
        
        assertEquals("id2", books[1].id)
        assertEquals("Fake Title 2", books[1].title)
        assertEquals("https://fake.url/2.jpg", books[1].imageUrl)
    }
}