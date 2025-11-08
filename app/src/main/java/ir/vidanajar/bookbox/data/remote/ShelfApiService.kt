package ir.vidanajar.bookbox.data.remote

import ir.vidanajar.bookbox.data.model.Shelf
import ir.vidanajar.bookbox.data.model.ShelfRequest
import ir.vidanajar.bookbox.data.model.ShelfResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ShelfApiService {

    @GET("/shelves")
    suspend fun getShelves(): List<Shelf>

    @POST("/shelves")
    suspend fun createShelf(
        @Body shelf: ShelfRequest
    ): ShelfResponse

//    @POST("/shelves/{shelfId}/books")
//    suspend fun addBookToShelf(
//        @Query("shelfId") shelfId: Int,
//        @Body request: AddBookRequest
//    ): ShelfResponse

    @POST("shelves/{shelfId}/books/{bookId}")
    suspend fun removeBookFromShelf(
        @Query("shelfId") shelfId: Int,
        @Query("bookId") bookId: Int,
    ): ShelfResponse

}
