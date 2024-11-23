package com.abanapps.di

import com.abanapps.book.data.network.KtorRemoteBookDataSource
import com.abanapps.book.data.network.RemoteBookDataSource
import com.abanapps.core.data.HttpClientFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModelOf
import com.abanapps.book.data.repository.DefaultBookRepository
import com.abanapps.book.domain.BookRepository
import com.abanapps.book.presentation.book_list.BookListViewModel
import com.abanapps.book.presentation.SharedViewModel
import com.abanapps.book.presentation.book_detail.BookDetailViewModel
import org.koin.core.module.Module

expect val platformModule:Module


val shareModule = module {

    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()


    viewModelOf(::BookListViewModel)
    viewModelOf(::SharedViewModel)
    viewModelOf(::BookDetailViewModel)

}

