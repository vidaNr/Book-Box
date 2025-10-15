package ir.vidanajar.bookbox.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore by preferencesDataStore("bookbox_prefs")

class TokenDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        val TOKEN_KEY = stringSetPreferencesKey("jwt_token")
    }

    val token: Flow<String?> = context.dataStore.data
        .map { prefs -> prefs[TOKEN_KEY].toString() }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = setOf(token)
        }
    }

    suspend fun clearToken() {
        context.dataStore.edit { prefs ->
            prefs.remove(TOKEN_KEY)
        }
    }

}