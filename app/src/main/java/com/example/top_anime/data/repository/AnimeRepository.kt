package com.example.top_anime.data.repository

import com.example.top_anime.common.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getTopAnimeList(): Flow<List<Anime>>
    fun getFavoriteAnimeList(): Flow<List<Anime>>
    fun toggleFavorite(animeId: String)
}
