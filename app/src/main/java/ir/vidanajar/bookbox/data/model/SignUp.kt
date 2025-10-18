package ir.vidanajar.bookbox.data.model

data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String,
)

data class SignUpResponse(
    val message: String,
)