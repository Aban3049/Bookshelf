package com.abanapps.book.data.repository

import com.abanapps.book.data.mappers.toBook
import com.abanapps.book.data.network.RemoteBookDataSource
import com.abanapps.book.domain.Book
import com.abanapps.book.domain.BookRepository
import com.abanapps.core.DataError
import com.abanapps.core.domain.Result
import com.abanapps.core.domain.map

class DefaultBookRepository(
    private val remoteDataSource: RemoteBookDataSource
) : BookRepository {

    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteDataSource.searchBooks(query = query).map { dto ->
            dto.results.map {
                it.toBook()
            }
        }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        return remoteDataSource.getBookDetails(bookId).map {
            it.description
        }
    }

}