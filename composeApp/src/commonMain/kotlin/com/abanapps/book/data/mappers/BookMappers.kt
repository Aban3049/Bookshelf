package com.abanapps.book.data.mappers

import com.abanapps.book.data.dto.SearchedBookDto
import com.abanapps.book.domain.Book

fun SearchedBookDto.toBook(): Book {
    return Book(
        id = id.substringAfter("/"),
        title = title,
        imageUrl = if (coverKey != null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg"
        },
        authors = authorNames ?: emptyList(),
        description = null,
        languages = languages ?: emptyList(),
        firstPublishYear = firstPublishYear.toString(),
        averageRating = ratingAverage,
        ratingCount = ratingsCount ?: 0,
        numPages = numPagesMedian,
        numEditors = numEditions ?: 0


    )
}