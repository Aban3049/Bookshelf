package com.abanapps.book.data.database

import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConvertor:RoomDatabaseConstructor<FavouriteBookDatabase> {

    override fun initialize(): FavouriteBookDatabase

}