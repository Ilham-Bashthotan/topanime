package com.example.top_anime.data.remote.service

import com.example.top_anime.data.remote.response.TopAnimeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeService {
    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int? = null
    ): TopAnimeResponse

    @GET("anime")
    suspend fun searchAnime(
        @Query("q") query: String
    ): TopAnimeResponse
}
