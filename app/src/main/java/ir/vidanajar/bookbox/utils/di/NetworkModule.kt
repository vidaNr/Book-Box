package ir.vidanajar.bookbox.utils.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.vidanajar.bookbox.data.model.Book
import ir.vidanajar.bookbox.data.repository.AuthRepository
import ir.vidanajar.bookbox.data.remote.AuthApiService
import ir.vidanajar.bookbox.data.remote.BookApiService
import ir.vidanajar.bookbox.data.remote.ShelfApiService
import ir.vidanajar.bookbox.data.repository.BookRepository
import ir.vidanajar.bookbox.data.repository.ShelfRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://192.168.100.23:3000/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    // Apis ==================

    @Provides
    @Singleton
    fun provideAuthApi(
        retrofit: Retrofit
    ): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBookApi(
        retrofit: Retrofit
    ): BookApiService {
        return retrofit.create(BookApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideShelfApi(
        retrofit: Retrofit
    ): ShelfApiService {
        return retrofit.create(ShelfApiService::class.java)
    }

    // Repositories ==================

    @Provides
    @Singleton
    fun provideBookRepository(
        api: BookApiService
    ): BookRepository {
        return BookRepository(api)
    }

    @Provides
    @Singleton
    fun provideShelfRepository(
        api: ShelfApiService
    ): ShelfRepository {
        return ShelfRepository(api)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthApiService
    ): AuthRepository {
        return AuthRepository(api)
    }
}