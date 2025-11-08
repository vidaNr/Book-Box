package ir.vidanajar.bookbox.data.model

import com.google.gson.annotations.SerializedName

data class Book(
    val id: Int,
    @SerializedName("openlibrary_id")
    val openLibraryId : String,
    val title: String,
    @SerializedName("author_name")
    val authors: String?,
    @SerializedName("first_publish_year")
    val year: Int? = null,
    @SerializedName("cover_url")
    val coverUrl: String? = null,
    val raw : Map<String, Any>? =null
)

data class BookSearchResponse(
    val source: String,
    val books: List<Book>,
)
