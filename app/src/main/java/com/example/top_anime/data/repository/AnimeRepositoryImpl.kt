package com.example.top_anime.data.repository

import com.example.top_anime.common.model.Anime
import com.example.top_anime.data.remote.response.TopAnimeResponse
import com.example.top_anime.data.remote.service.AnimeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class AnimeRepositoryImpl(
    private val service: AnimeService
) : AnimeRepository {

    private val favoriteIds = MutableStateFlow<Set<String>>(emptySet())
    private val cachedTopList = MutableStateFlow<List<Anime>>(emptyList())
    private val cachedAllById = MutableStateFlow<Map<String, Anime>>(emptyMap())
    private val searchQuery = MutableStateFlow("")

    override fun getTopAnimeList(): Flow<List<Anime>> {
        return searchQuery.flatMapLatest { query ->
            flow {
                val response: TopAnimeResponse =
                    if (query.isBlank()) service.getTopAnime() else service.searchAnime(query)
                val baseList = response.data.map { item ->
                    Anime(
                        id = item.id.toString(),
                        title = item.title ?: "-",
                        imageUrl = item.images?.jpg?.largeImageUrl ?: item.images?.jpg?.imageUrl ?: "",
                        type = item.type ?: "-",
                        episodes = item.episodes?.toString() ?: "-",
                        score = item.score?.toString() ?: "-",
                        rank = item.rank?.toString() ?: "-",
                        isFavorite = false
                    )
                }
                cachedTopList.value = baseList
                cachedAllById.value = cachedAllById.value + baseList.associateBy { it.id }
                emit(baseList)
            }
        }.combine(favoriteIds) { list, favIds ->
            list.map { it.copy(isFavorite = favIds.contains(it.id)) }
        }
    }

    override fun getFavoriteAnimeList(): Flow<List<Anime>> {
        // Derive favorites from accumulated cache across queries
        return combine(cachedAllById, favoriteIds) { cacheMap, favIds ->
            favIds.mapNotNull { id -> cacheMap[id]?.copy(isFavorite = true) }
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

    override fun setSearchQuery(query: String) {
        searchQuery.value = query
    }
}
