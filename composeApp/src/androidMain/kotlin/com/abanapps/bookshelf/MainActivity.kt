package com.abanapps.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.abanapps.book.presentation.book_list.BookListScreen
import com.abanapps.book.presentation.book_list.BookListState
import com.abanapps.book.presentation.book_list.books

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