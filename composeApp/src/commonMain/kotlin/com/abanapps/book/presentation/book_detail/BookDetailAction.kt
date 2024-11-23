package com.abanapps.book.presentation.book_detail

import com.abanapps.book.domain.Book

sealed interface BookDetailAction {
    data object OnBackClick:BookDetailAction
    data object OnFavouriteClick:BookDetailAction
    data class OnSelectedBookChange(val book:Book):BookDetailAction
}