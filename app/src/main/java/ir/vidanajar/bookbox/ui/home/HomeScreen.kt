package ir.vidanajar.bookbox.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.vidanajar.bookbox.data.model.Book
import ir.vidanajar.bookbox.ui.home.components.HomeErrorState
import ir.vidanajar.bookbox.ui.home.components.HomeHistoryState
import ir.vidanajar.bookbox.ui.home.components.HomeIdleState
import ir.vidanajar.bookbox.ui.home.components.HomeLoadingState
import ir.vidanajar.bookbox.ui.home.components.HomeSuccessState


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onBookClicked: (Book) -> Unit,
) {

    val query by viewModel.query.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize(), contentAlignment = Alignment.TopStart
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier.height(24.dp))

            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = query,
                onValueChange = viewModel::onQueryChanged,
                singleLine = true,
                placeholder = { Text("Search") },
            )

            Spacer(modifier.height(24.dp))

            when (uiState) {
                is HomeUiState.Idle -> HomeIdleState ()
                is HomeUiState.Loading -> HomeLoadingState ()
                is HomeUiState.History -> {
                    val history = (uiState as HomeUiState.History).history
                    HomeHistoryState (modifier,history)
                }
                is HomeUiState.Success -> {
                    val books = (uiState as HomeUiState.Success).books
                    HomeSuccessState (
                        modifier = modifier,
                        books = books,
                        onBookClicked = {
                            books.forEach {
                                onBookClicked(it)
                            }
                        },
                    )
                }
                is HomeUiState.Error -> {
                    val message = (uiState as HomeUiState.Error).message
                    HomeErrorState (modifier = modifier, message = message)
                }
            }

        }
    }
}

