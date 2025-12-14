package com.example.top_anime.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun removeFavorite(animeId: String) {
        repository.toggleFavorite(animeId)
    }

    fun showConfirmDialog(animeId: String) {
        _uiState.update { it.copy(pendingAnimeId = animeId, showDialog = true) }
    }

    fun hideConfirmDialog() {
        _uiState.update { it.copy(showDialog = false, pendingAnimeId = null) }
    }

    fun confirmRemoveFavorite() {
        val id = _uiState.value.pendingAnimeId
        if (id != null) {
            removeFavorite(id)
        }
        hideConfirmDialog()
    }
}
