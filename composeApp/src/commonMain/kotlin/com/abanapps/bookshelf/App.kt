package com.abanapps.bookshelf

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

@Composable
@Preview
fun App(engine: HttpClientEngine) {
    MaterialTheme {
        BookListScreenRoot(viewModel = remember {
            BookListViewModel(
                bookRepository =
                DefaultBookRepository(
                    remoteDataSource = KtorRemoteBookDataSource(
                        httpClient = HttpClientFactory.create(
                            engine = engine
                        )
                    )
                )
            )
        }, onBookClick = {})

//        BookListScreen(
//            state = BookListState(
//                searchResults = books
//            ), onAction = {})

    }
}