package ir.vidanajar.bookbox.data.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val user: User,
    val token: String
)
