package com.example.top_anime.data.repository

import com.example.top_anime.common.model.Anime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class AnimeRepositoryImpl : AnimeRepository {
    
    private val favoriteIds = MutableStateFlow<Set<String>>(emptySet())
    
    private val allAnimeList = listOf(
        Anime(
            "1", "Sousou no Frieren",
            "https://cdn.myanimelist.net/images/anime/1015/138006.jpg",
            "TV", "28", "9.28", "1"
        ),
        Anime(
            "2", "Chainsaw Man Movie: Reze-hen",
            "https://cdn.myanimelist.net/images/anime/1763/150638.jpg",
            "Movie", "1", "9.17", "2"
        ),
        Anime(
            "3", "Fullmetal Alchemist: Brotherhood",
            "https://cdn.myanimelist.net/images/anime/1223/96541.jpg",
            "TV", "64", "9.10", "3"
        ),
        Anime(
            "4", "Steins;Gate",
            "https://cdn.myanimelist.net/images/anime/5/73199.jpg",
            "TV", "24", "9.08", "4"
        )
    )
    
    override fun getTopAnimeList(): Flow<List<Anime>> {
        return favoriteIds.map { favIds ->
            allAnimeList.map { anime ->
                anime.copy(isFavorite = favIds.contains(anime.id))
            }
        }
    }

    override fun getFavoriteAnimeList(): Flow<List<Anime>> {
        return favoriteIds.map { favIds ->
            allAnimeList
                .filter { favIds.contains(it.id) }
                .map { it.copy(isFavorite = true) }
        }
    }
    
    override fun toggleFavorite(animeId: String) {
        val currentFavorites = favoriteIds.value.toMutableSet()
        if (currentFavorites.contains(animeId)) {
            currentFavorites.remove(animeId)
        } else {
            currentFavorites.add(animeId)
        }
        favoriteIds.value = currentFavorites
    }
}
