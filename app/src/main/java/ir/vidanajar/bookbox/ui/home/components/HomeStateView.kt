package ir.vidanajar.bookbox.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.vidanajar.bookbox.data.model.Book
import ir.vidanajar.bookbox.ui.home.HomeViewModel

@Composable
fun HomeIdleState() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Start typing to search...")
    }
}


@Composable
fun HomeLoadingState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun HomeHistoryState(
    modifier: Modifier = Modifier,
    history: List<String>,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Recent searches", style = MaterialTheme.typography.titleLarge)
        LazyColumn {
            items(history) { item ->
                TextButton(onClick = { viewModel.onQueryChanged(item) }) {
                    Text(item, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
fun HomeSuccessState(
    modifier: Modifier = Modifier,
    books: List<Book>,
    onBookClicked: (Book) -> Unit,
) {
    LazyColumn(modifier.padding(bottom = 24.dp)) {
        items(books) { item ->
            BookItem(modifier, item, onBookClick = { onBookClicked(item) })
        }
    }
}

@Composable
fun HomeErrorState(modifier: Modifier = Modifier, message: String) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Error: $message", color = MaterialTheme.colorScheme.error)
    }
}