package ir.vidanajar.bookbox.data.model

data class Shelf(
    val id: Int,
    val name: String,
    val books: List<Book>
)

data class ShelfRequest (
    val name: String
)

data class ShelfResponse(
    val id: Int,
    val name: String
)