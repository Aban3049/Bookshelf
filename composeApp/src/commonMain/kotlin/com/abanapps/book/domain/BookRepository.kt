package com.abanapps.book.domain

import com.abanapps.core.DataError
import com.abanapps.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query:String):Result<List<Book>,DataError.Remote>
}