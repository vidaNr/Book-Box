package ir.vidanajar.bookbox.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vidanajar.bookbox.data.model.Book
import ir.vidanajar.bookbox.data.repository.BookRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HomeUiState {
    object Idle : HomeUiState()
    object Loading : HomeUiState()
    data class Success(val books: List<Book>) : HomeUiState()
    data class History(val history: List<String>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookRepository: BookRepository,
//    private val shelfRepository: ShelfRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    val searchHistory = bookRepository.getSearchHistory()

    init {
        observeQueryChanges()
        loadSearchHistory()
    }

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
    }

    private fun observeQueryChanges() {
        _query.debounce(1000)
            .distinctUntilChanged()
            .onEach { q ->
                if (q.isBlank()) {
                    loadSearchHistory()
                } else {
                    searchBooks(q)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun loadSearchHistory() {
        viewModelScope.launch {
            searchHistory.collect { list ->
                _uiState.value = if (list.isEmpty()) HomeUiState.Idle else HomeUiState.History(list)
            }
        }
    }

    private fun searchBooks(query: String) {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                val result = bookRepository.searchBook(query)
                bookRepository.saveSearchHistory(query)
                _uiState.value = if (result.isEmpty())
                    HomeUiState.Error("No results found")
                else
                    HomeUiState.Success(result)

                Log.d("TAG", "searchBooks(try): $result")
            } catch (err: Exception) {
                _uiState.value = HomeUiState.Error(err.message ?: "Unknown Error")
                Log.d("TAG", "searchBooks(catch): ${err.message}")
            }
        }
    }
}