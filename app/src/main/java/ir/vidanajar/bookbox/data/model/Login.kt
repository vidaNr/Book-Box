package ir.vidanajar.bookbox.data.model

import ir.vidanajar.bookbox.data.remote.UserData

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val user: UserData,
    val token: String
)
