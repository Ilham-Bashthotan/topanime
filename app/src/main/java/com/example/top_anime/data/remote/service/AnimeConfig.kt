package com.example.top_anime.data.remote.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AnimeConfig {
    const val BASE_URL = "https://api.jikan.moe/v4/"

    fun getAnimeService(): AnimeService {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        return retrofit.create(AnimeService::class.java)
    }
}
