package com.abanapps.bookshelf

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.abanapps.book.presentation.book_list.BookListScreen
import com.abanapps.book.presentation.book_list.BookListScreenRoot
import com.abanapps.book.presentation.book_list.BookListState
import com.abanapps.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        BookListScreenRoot(viewModel = remember {
            BookListViewModel()
        }, onBookClick = {})

//        BookListScreen(
//            state = BookListState(
//                searchResults = books
//            ), onAction = {})

    }
}