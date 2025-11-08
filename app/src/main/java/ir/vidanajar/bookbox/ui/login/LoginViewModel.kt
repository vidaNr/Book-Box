package ir.vidanajar.bookbox.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vidanajar.bookbox.data.repository.AuthRepository
import ir.vidanajar.bookbox.data.local.TokenDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val token : String) : LoginState()
    data class Error(val message: String) : LoginState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenStore: TokenDataStore
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password: String) {

        if (email.isBlank() || password.isBlank()) return

        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val response = authRepository.login(email, password)
                tokenStore.saveToken(response.token)
                _loginState.value = LoginState.Success(response.token)
                Log.d("TAG", "user login was Successful!")
            } catch (err: Exception) {
                _loginState.value = LoginState.Error(err.message ?: "Login failed")
                Log.d("TAG", "user login Failed: $err")
            }
        }
    }
}