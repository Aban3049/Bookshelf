package com.abanapps.book.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abanapps.book.domain.Book
import com.abanapps.book.domain.BookRepository
import com.abanapps.core.domain.onError
import com.abanapps.core.domain.onSuccess
import com.abanapps.core.presentation.toUitText
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookListViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BookListState())
    val state = _state.onStart {
        if (cachedBooks.isEmpty()) {
            observeSearchQuery()
        }
        observeFavouriteBooks()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L), _state.value
    )

    private var cachedBooks = emptyList<Book>()
    private var searchJob: Job? = null
    private var observeFavouriteJob: Job? = null

    fun onAction(action: BookListAction) {

        when (action) {

            is BookListAction.OnBookClick -> {

            }

            is BookListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }

            is BookListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }


        }

    }

    private fun observeFavouriteBooks() {
        observeFavouriteJob?.cancel()
        observeFavouriteJob = bookRepository.getFavouriteBooks().onEach { favourite ->
            _state.update {
                it.copy(
                    favouriteBooks = favourite
                )
            }
        }.launchIn(viewModelScope)
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {

        state.map {
            it.searchQuery
        }.distinctUntilChanged().debounce(500L)
            .onEach { query ->
                when {

                    query.isBlank() -> {

                        _state.update {
                            it.copy(
                                errorMessage = null,
                                searchResults = cachedBooks
                            )
                        }

                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchBooks(query)
                    }

                }
            }.launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true,

                )
        }

        bookRepository.searchBooks(query).onSuccess { searchResutls ->
            _state.update {
                it.copy(
                    isLoading = false,
                    errorMessage = null,
                    searchResults = searchResutls
                )
            }
        }.onError { error ->
            _state.update {
                it.copy(
                    searchResults = emptyList(),
                    isLoading = false,
                    errorMessage = error.toUitText()
                )
            }
        }

    }

}