package com.abanapps.book.presentation

import androidx.lifecycle.ViewModel
import com.abanapps.book.domain.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel : ViewModel() {

    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook = _selectedBook.asStateFlow()

    fun onSelectedBook(book: Book?) {
        _selectedBook.value = book
    }

}