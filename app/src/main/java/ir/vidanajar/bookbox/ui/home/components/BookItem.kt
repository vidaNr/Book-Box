package ir.vidanajar.bookbox.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ir.vidanajar.bookbox.R
import ir.vidanajar.bookbox.data.model.Book

@Composable
fun BookItem(modifier: Modifier, book: Book, onBookClick: () -> Unit) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = onBookClick
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = book.coverUrl ?: "",
                contentDescription = "${book.title} cover",
                modifier= modifier.size(40.dp),
                placeholder = painterResource(R.drawable.img_gallery),
                error = painterResource(R.drawable.img_gallery)
            )

            Spacer(modifier.width(24.dp))
            Column {
                Text(book.title, style = MaterialTheme.typography.titleMedium)
                Text(book.authors ?: "Unknown Author", style = MaterialTheme.typography.bodySmall)
                if (book.year != null) {
                    Text("Published: ${book.year}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}