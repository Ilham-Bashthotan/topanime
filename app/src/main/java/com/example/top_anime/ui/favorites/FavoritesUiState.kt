package com.example.top_anime.ui.favorites

import com.example.top_anime.common.model.Anime

data class FavoritesUiState(
    val favoriteAnimeList: List<Anime> = emptyList(),
    val showDialog: Boolean = false,
    val pendingAnime: Anime? = null
)
