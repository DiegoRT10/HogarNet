package com.example.inventory.network

import com.example.inventory.data.Dog
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json

import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET


private const val BASE_URL =
    "https://api.thedogapi.com/v1/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface DogApiService {
    @GET("images/search?limit=20")
    suspend fun getPhotos(): List<Dog>
}

object DogApi {
    val retrofitService : DogApiService by lazy {
        retrofit.create(DogApiService::class.java)
    }
}