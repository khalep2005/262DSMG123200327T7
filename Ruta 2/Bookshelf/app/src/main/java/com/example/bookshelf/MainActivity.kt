package com.example.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.BooksViewModel
import com.example.bookshelf.ui.BookshelfScreen
import com.example.bookshelf.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookshelfTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val booksViewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)
                    BookshelfScreen(
                        booksUiState = booksViewModel.booksUiState,
                        retryAction = { booksViewModel.getBooks("jazz history") },
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = innerPadding
                    )
                }
            }
        }
    }
}