package com.example.top_anime.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.top_anime.common.model.Anime
import com.example.top_anime.data.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState

    init {
        observeFavoriteAnime()
    }

    private fun observeFavoriteAnime() {
        viewModelScope.launch {
            repository.getFavoriteAnimeList()
                .collectLatest { list ->
                    _uiState.update { state ->
                        state.copy(
                            favoriteAnimeList = list,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
        }
    }

    fun removeFavorite(anime: Anime) {
        viewModelScope.launch {
            setLoading(true)
            try {
                repository.toggleFavorite(anime)
            } catch (e: Exception) {
                setError(e.message)
            } finally {
                setLoading(false)
            }
        }
    }

    fun showConfirmDialog(anime: Anime) {
        _uiState.update { state ->
            state.copy(
                pendingAnime = anime,
                showDialog = true
            )
        }
    }


    fun hideConfirmDialog() {
        _uiState.update { state ->
            state.copy(
                showDialog = false,
                pendingAnime = null
            )
        }
    }

    fun confirmRemoveFavorite() {
        _uiState.value.pendingAnime?.let { anime ->
            removeFavorite(anime)
        }
        hideConfirmDialog()
    }

    private fun setLoading(value: Boolean) {
        _uiState.update { state ->
            state.copy(isLoading = value)
        }
    }

    private fun setError(message: String?) {
        _uiState.update { state ->
            state.copy(errorMessage = message)
        }
    }
}
