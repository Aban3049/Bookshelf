package com.abanapps.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.abanapps.book.app.App
import com.abanapps.book.domain.Book
import com.abanapps.book.presentation.book_list.BookListScreen
import com.abanapps.book.presentation.book_list.BookListState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    BookListScreen(
        state = BookListState(
            searchResults = books
        ), onAction = {})
}


val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://",
        authors = listOf("Aban", "Aban", "Aban"),
        description = "Description $it",
        languages = emptyList(),
        firstPublishYear = null,
        averageRating = 4.67859,
        ratingCount = 5,
        numPages = 100,
        numEditors = 3
    )
}
