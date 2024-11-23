package com.abanapps.book.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer


@Serializable
data class SearchResponseDto(
    @SerialName("docs") val results:List<SearchedBookDto>
)
