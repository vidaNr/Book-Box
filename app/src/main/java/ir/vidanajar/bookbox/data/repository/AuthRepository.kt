package ir.vidanajar.bookbox.data.repository

import ir.vidanajar.bookbox.data.model.LoginRequest
import ir.vidanajar.bookbox.data.model.LoginResponse
import ir.vidanajar.bookbox.data.model.SignUpRequest
import ir.vidanajar.bookbox.data.model.SignUpResponse
import ir.vidanajar.bookbox.data.remote.AuthApiService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApiService
) {
    suspend fun login(email: String, password: String) : LoginResponse =
        api.login(LoginRequest(email, password))

    suspend fun signUp(name: String, email: String, password: String) : SignUpResponse =
        api.signUp(SignUpRequest(name, email, password))
}