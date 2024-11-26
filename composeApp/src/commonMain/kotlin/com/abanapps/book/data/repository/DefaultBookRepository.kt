package com.abanapps.book.data.repository

import androidx.sqlite.SQLiteException
import com.abanapps.book.data.database.FavouriteBookDao
import com.abanapps.book.data.mappers.toBook
import com.abanapps.book.data.mappers.toBookEntity
import com.abanapps.book.data.network.RemoteBookDataSource
import com.abanapps.book.domain.Book
import com.abanapps.book.domain.BookRepository
import com.abanapps.core.DataError
import com.abanapps.core.domain.EmptyResult
import com.abanapps.core.domain.Result
import com.abanapps.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteDataSource: RemoteBookDataSource,
    private val favouriteBookDao: FavouriteBookDao
) : BookRepository {

    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteDataSource.searchBooks(query = query).map { dto ->
            dto.results.map {
                it.toBook()
            }
        }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {

        val localResult = favouriteBookDao.getFavouriteBook(bookId)

        return if (localResult == null) {
            remoteDataSource.getBookDetails(bookId)
                .map { it.description }
        } else {
            Result.Success(localResult.description)
        }
    }

    override fun getFavouriteBooks(): Flow<List<Book>> {
        return favouriteBookDao.getFavouriteBooks().map { bookEntities ->
            bookEntities.map { book ->
                book.toBook()
            }
        }
    }

    override fun isFavourite(id: String): Flow<Boolean> {
        return favouriteBookDao.getFavouriteBooks().map { bookEntities ->
            bookEntities.any {
                it.id == id
            }
        }
    }

    override suspend fun markAsFavourite(book: Book): EmptyResult<DataError.Local> {
        return try {
            favouriteBookDao.upsert(book.toBookEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavourite(id: String) {
        favouriteBookDao.deleteFavouriteBook(id)
    }


}