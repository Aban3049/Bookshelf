package com.abanapps

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.abanapps.book.data.network.KtorRemoteBookDataSource
import com.abanapps.book.data.repository.DefaultBookRepository
import com.abanapps.book.presentation.book_list.BookListScreenRoot
import com.abanapps.book.presentation.book_list.BookListViewModel
import com.abanapps.core.data.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {

    val viewModel = koinViewModel<BookListViewModel>()

    MaterialTheme {
        BookListScreenRoot(viewModel = viewModel,onBookClick = {})


    }
}