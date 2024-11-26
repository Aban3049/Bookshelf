package com.abanapps.book.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String,
    val languages: List<String>,
    val authors: List<String>,
    val firstPublishingYear: String?,
    val ratingAverage: Double?,
    val ratingCount: Int,
    val numPagesMedian: Int?,
    val numEditors: Int
)
