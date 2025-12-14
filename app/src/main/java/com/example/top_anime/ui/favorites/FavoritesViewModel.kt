package com.example.top_anime.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.top_anime.common.model.Anime
import com.example.top_anime.data.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState

    init {
        viewModelScope.launch {
            repository.getFavoriteAnimeList().collect { list ->
                _uiState.update { it.copy(favoriteAnimeList = list) }
            }
        }
    }

    fun removeFavorite(anime: Anime) {
        viewModelScope.launch {
            repository.toggleFavorite(anime)
        }
    }

    fun showConfirmDialog(anime: Anime) {
        _uiState.update { it.copy(pendingAnime = anime, showDialog = true) }
    }

    fun hideConfirmDialog() {
        _uiState.update { it.copy(showDialog = false, pendingAnime = null) }
    }

    fun confirmRemoveFavorite() {
        val anime = _uiState.value.pendingAnime
        if (anime != null) {
            removeFavorite(anime)
        }
        hideConfirmDialog()
    }
}
