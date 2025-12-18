package com.example.top_anime.ui.animeList

import com.example.top_anime.common.model.Anime

data class AnimeListUiState(
    val animeList: List<Anime> = emptyList(),
    val showDialog: Boolean = false,
    val pendingAnime: Anime? = null,
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
