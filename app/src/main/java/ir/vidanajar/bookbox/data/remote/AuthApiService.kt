package ir.vidanajar.bookbox.data.remote

import retrofit2.http.Body
import retrofit2.http.POST


data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val user: UserData,
    val token: String
)
data class UserData(
    val id: Int,
    val email: String,
    val name: String,
)


interface AuthApiService {

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

}