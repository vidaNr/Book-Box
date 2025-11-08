package ir.vidanajar.bookbox.data.remote

import ir.vidanajar.bookbox.data.model.LoginRequest
import ir.vidanajar.bookbox.data.model.LoginResponse
import ir.vidanajar.bookbox.data.model.SignUpRequest
import ir.vidanajar.bookbox.data.model.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("/auth/register")
    suspend fun signUp(@Body request: SignUpRequest): SignUpResponse
}