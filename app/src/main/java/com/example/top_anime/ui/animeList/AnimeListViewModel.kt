package com.example.top_anime.ui.animeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.top_anime.common.model.Anime
import com.example.top_anime.data.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeListViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnimeListUiState())
    val uiState: StateFlow<AnimeListUiState> = _uiState

    init {
        observeTopAnime()
    }

    private fun observeTopAnime() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true)
            }

            try {
                repository.getTopAnimeList()
                    .collectLatest { list ->
                        _uiState.update { state ->
                            state.copy(
                                animeList = list,
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        errorMessage = e.message
                    )
                }
            }
        }
    }

    fun toggleFavorite(anime: Anime) {
        viewModelScope.launch {
            try {
                repository.toggleFavorite(anime)
            } catch (e: Exception) {
                setError(e.message)
            }
        }
    }

    fun updateSearchText(text: String) {
        _uiState.update { state ->
            state.copy(searchQuery = text)
        }
    }

    fun search() {
        try {
            repository.setSearchQuery(_uiState.value.searchQuery)
        } catch (e: Exception) {
            setError(e.message)
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

    fun confirmToggleFavorite() {
        _uiState.value.pendingAnime?.let { anime ->
            toggleFavorite(anime)
        }
        hideConfirmDialog()
    }

    private fun setError(message: String?) {
        _uiState.update { state ->
            state.copy(errorMessage = message)
        }
    }
}
