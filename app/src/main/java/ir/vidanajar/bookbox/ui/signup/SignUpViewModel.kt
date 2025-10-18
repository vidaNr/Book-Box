package ir.vidanajar.bookbox.ui.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vidanajar.bookbox.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SignUpState {
    object Idle : SignUpState()
    object Loading : SignUpState()
    object Success : SignUpState()
    data class Error(val message: String) : SignUpState()
}

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {


    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState = _signUpState.asStateFlow()


    fun signUp(email: String, password: String, name: String) {

        viewModelScope.launch {
            _signUpState.value = SignUpState.Loading
            try {
                val response = authRepository.signUp(name, email, password)
                Log.d("TAG", "signUp Successful: $response")
                _signUpState.value = SignUpState.Success
            } catch (err: Exception) {
                _signUpState.value = SignUpState.Error(err.message ?: "Unknown error")
                Log.d("TAG", "user signUp Failed: $err")
            }
        }
    }
}