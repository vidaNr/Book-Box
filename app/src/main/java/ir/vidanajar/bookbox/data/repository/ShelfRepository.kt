package ir.vidanajar.bookbox.data.repository

import ir.vidanajar.bookbox.data.model.Shelf
import ir.vidanajar.bookbox.data.model.ShelfRequest
import ir.vidanajar.bookbox.data.model.ShelfResponse
import ir.vidanajar.bookbox.data.remote.ShelfApiService
import javax.inject.Inject

class ShelfRepository @Inject constructor(
    private val api: ShelfApiService
) {

    suspend fun getShelf(): List<Shelf> {
        return api.getShelves()
    }

    suspend fun createShelf(name: String): ShelfResponse {
        return api.createShelf(ShelfRequest(name))
    }

//    suspend fun addBookToShelf(shelfId: Int, bookId: String, bookData: Map<String, Any>): ShelfResponse{
//        return api.addBookToShelf(shelfId, AddBookRequest(bookId,bookData))
//    }
}