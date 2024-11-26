package com.abanapps.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.abanapps.book.data.database.DatabaseFactory
import com.abanapps.book.data.database.FavouriteBookDatabase
import com.abanapps.book.data.network.KtorRemoteBookDataSource
import com.abanapps.book.data.network.RemoteBookDataSource
import com.abanapps.book.data.repository.DefaultBookRepository
import com.abanapps.book.domain.BookRepository
import com.abanapps.book.presentation.SharedViewModel
import com.abanapps.book.presentation.book_detail.BookDetailViewModel
import com.abanapps.book.presentation.book_list.BookListViewModel
import com.abanapps.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module


val shareModule = module {

    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()


    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<FavouriteBookDatabase>().favouriteBookDao }

    viewModelOf(::BookListViewModel)
    viewModelOf(::SharedViewModel)
    viewModelOf(::BookDetailViewModel)

}

