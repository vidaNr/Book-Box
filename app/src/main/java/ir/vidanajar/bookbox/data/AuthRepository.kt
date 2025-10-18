package ir.vidanajar.bookbox.data

import ir.vidanajar.bookbox.data.remote.AuthApiService
import ir.vidanajar.bookbox.data.remote.LoginRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: AuthApiService
) {
    suspend fun login(email: String, password: String) =
        apiService.login(LoginRequest(email, password))
}