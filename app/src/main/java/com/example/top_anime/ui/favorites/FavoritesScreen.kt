package com.example.top_anime.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.top_anime.ui.components.AnimeCard
import com.example.top_anime.ui.components.ConfirmationDialog
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoritesScreen(
    onBackClick: () -> Unit = {},
    viewModel: FavoritesViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    ConfirmationDialog(
        showDialog = uiState.showDialog && uiState.pendingAnime != null,
        title = "Batalkan Favorit",
        message = "Apakah Anda yakin ingin membatalkan favorit anime ini?",
        onConfirm = {
            viewModel.confirmRemoveFavorite()
        },
        onDismiss = {
            viewModel.hideConfirmDialog()
        }
    )
    
    Scaffold(
        topBar = {
            FavoritesTopBar(onBackClick)
        },
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(padding)
        ) {
            items(uiState.favoriteAnimeList) { anime ->
                AnimeCard(
                    anime = anime,
                    onFavoriteClick = { _, _ ->
                        viewModel.showConfirmDialog(anime)
                    }
                )
            }
        }
    }
}
