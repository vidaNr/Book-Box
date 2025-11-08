package ir.vidanajar.bookbox.data.repository

import ir.vidanajar.bookbox.data.model.Book
import ir.vidanajar.bookbox.data.remote.BookApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val api: BookApiService
) {
    private val searchHistoryFlow = MutableStateFlow<List<String>>(emptyList())

    suspend fun searchBook(query : String) : List<Book>{
        val response = api.searchBook(query)
        return response.books
    }

    fun getSearchHistory() :Flow<List<String>> = searchHistoryFlow

    fun saveSearchHistory(query: String) {
        val current = searchHistoryFlow.value.toMutableList()
        if (!current.contains(query)){
            current.add(0,query)
            if (current.size >10) current.removeAt(current.lastIndex)
            searchHistoryFlow.value = current
        }
    }
}