package com.abanapps.book.presentation.book_detail

import com.abanapps.book.domain.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavourite: Boolean = false,
    val book: Book? = null
)
