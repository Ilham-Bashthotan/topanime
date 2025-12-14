package com.example.top_anime.data.repository

import com.example.top_anime.common.model.Anime
import com.example.top_anime.data.local.FavoriteAnimeDao
import com.example.top_anime.data.local.toDomain
import com.example.top_anime.data.local.toEntity
import com.example.top_anime.data.remote.response.TopAnimeResponse
import com.example.top_anime.data.remote.service.AnimeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class AnimeRepositoryImpl(
    private val service: AnimeService,
    private val favoriteDao: FavoriteAnimeDao
) : AnimeRepository {

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
                emit(baseList)
            }
        }.combine(favoriteDao.getAllFavorites()) { list, favorites ->
            val favIds = favorites.map { it.id }.toSet()
            list.map { it.copy(isFavorite = favIds.contains(it.id)) }
        }
    }

    override fun getFavoriteAnimeList(): Flow<List<Anime>> {
        return favoriteDao.getAllFavorites().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun toggleFavorite(anime: Anime) {
        val existing = favoriteDao.getFavoriteById(anime.id)
        if (existing != null) {
            favoriteDao.deleteFavoriteById(anime.id)
        } else {
            favoriteDao.insertFavorite(anime.toEntity())
        }
    }

    override fun setSearchQuery(query: String) {
        searchQuery.value = query
    }
}
