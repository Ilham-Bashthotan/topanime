package com.example.top_anime.ui.animeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.top_anime.common.model.Anime
import com.example.top_anime.data.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class AnimeListViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    val animeList: StateFlow<List<Anime>> = repository.getTopAnimeList()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    
    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()
    
    private val _pendingAnimeId = MutableStateFlow<String?>(null)
    val pendingAnimeId: StateFlow<String?> = _pendingAnimeId.asStateFlow()
    
    fun toggleFavorite(animeId: String) {
        repository.toggleFavorite(animeId)
    }
    
    fun showConfirmDialog(animeId: String) {
        _pendingAnimeId.value = animeId
        _showDialog.value = true
    }
    
    fun hideConfirmDialog() {
        _showDialog.value = false
        _pendingAnimeId.value = null
    }
    
    fun confirmToggleFavorite() {
        _pendingAnimeId.value?.let { toggleFavorite(it) }
        hideConfirmDialog()
    }
}
