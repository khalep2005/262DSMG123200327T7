package com.example.bookshelf.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.Book

@Composable
fun BookshelfScreen(
    booksUiState: BooksUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (booksUiState) {
        is BooksUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is BooksUiState.Success -> BooksGridScreen(
            books = booksUiState.books,
            modifier = modifier.padding(
                top = contentPadding.calculateTopPadding(),
                bottom = contentPadding.calculateBottomPadding()
            )
        )
        is BooksUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        // En una app real se usaría un CircularProgressIndicator,
        // pero para simplificar usamos un texto o la imagen por defecto
        Text(text = "Loading...")
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Failed to load")
        Button(onClick = retryAction) {
            Text("Retry")
        }
    }
}

@Composable
fun BooksGridScreen(
    books: List<Book>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = books, key = { book -> book.id }) { book ->
            BookCard(
                book = book,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(0.7f) // Proporción común para libros
            )
        }
    }
}

@Composable
fun BookCard(book: Book, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(book.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = book.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize() // Fill the strict boundaries defined in the Grid item
        )
    }
}