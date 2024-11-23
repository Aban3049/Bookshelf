package com.abanapps.book.data.network

import com.abanapps.book.data.dto.SearchResponseDto
import com.abanapps.book.data.dto.SearchedBookDto
import com.abanapps.core.DataError
import com.abanapps.core.domain.Result

interface RemoteBookDataSource {

    suspend fun searchBooks(
        query:String,
        resultLimit:Int?=null
    ):Result<SearchResponseDto,DataError.Remote>

}