package ir.vidanajar.bookbox.data.remote

import ir.vidanajar.bookbox.data.model.Book
import ir.vidanajar.bookbox.data.model.BookSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApiService {

    @GET("/books/search")
    suspend fun searchBook(
        @Query("q") query: String,
//        @Query("page") page : Int =1,
//        @Query("limit") limit : Int =10
    ) : BookSearchResponse


    @GET("/books/")
    suspend fun getBooksCached(
        @Path("id") id: String,
    ) : Book

}
