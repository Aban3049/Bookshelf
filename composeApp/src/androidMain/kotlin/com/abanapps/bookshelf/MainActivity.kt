package com.abanapps.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.abanapps.book.presentation.book_list.BookListScreen
import com.abanapps.book.presentation.book_list.BookListState
import com.abanapps.book.presentation.book_list.books
import io.ktor.client.engine.okhttp.OkHttp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(
                engine = remember {
                    OkHttp.create()
                }
            )
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