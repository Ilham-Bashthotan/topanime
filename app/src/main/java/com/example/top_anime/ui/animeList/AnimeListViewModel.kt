package com.example.top_anime.ui.animeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.top_anime.common.model.Anime
import com.example.top_anime.data.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeListViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnimeListUiState())
    val uiState: StateFlow<AnimeListUiState> = _uiState

    init {
        viewModelScope.launch {
            repository.getTopAnimeList().collect { list ->
                _uiState.update { it.copy(animeList = list) }
            }
        }
    }

    fun search(query: String) {
        repository.setSearchQuery(query)
    }

    fun toggleFavorite(anime: Anime) {
        viewModelScope.launch {
            repository.toggleFavorite(anime)
        }
    }
    
    fun updateSearchText(text: String) {
        _uiState.update { it.copy(searchQuery = text) }
    }

    fun showConfirmDialog(anime: Anime) {
        _uiState.update { it.copy(pendingAnime = anime, showDialog = true) }
    }

    fun hideConfirmDialog() {
        _uiState.update { it.copy(showDialog = false, pendingAnime = null) }
    }

    fun confirmToggleFavorite() {
        val anime = _uiState.value.pendingAnime
        if (anime != null) {
            toggleFavorite(anime)
        }
        hideConfirmDialog()
    }
    
    fun search() {
        repository.setSearchQuery(_uiState.value.searchQuery)
    }
}
