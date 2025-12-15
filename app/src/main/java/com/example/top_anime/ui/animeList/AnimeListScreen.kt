package com.example.top_anime.ui.animeList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.top_anime.ui.components.AnimeCard
import com.example.top_anime.ui.components.ConfirmationDialog
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AnimeListScreen(
    onNavigateToFavorites: () -> Unit = {},
    viewModel: AnimeListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val showSearchState = remember { mutableStateOf(false) }

    ConfirmationDialog(
        showDialog = uiState.showDialog && uiState.pendingAnime != null,
        title = "Batalkan Favorit",
        message = "Apakah Anda yakin ingin membatalkan favorit anime ini?",
        onConfirm = {
            viewModel.confirmToggleFavorite()
        },
        onDismiss = {
            viewModel.hideConfirmDialog()
        }
    )

    if (showSearchState.value) {
        AlertDialog(
            onDismissRequest = { showSearchState.value = false },
            title = { Text("Cari Anime") },
            text = {
                OutlinedTextField(
                    value = uiState.searchQuery,
                    onValueChange = { viewModel.updateSearchText(it) },
                    singleLine = true,
                    label = { Text("Nama anime") }
                )
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.search()
                    showSearchState.value = false
                }) {
                    Text("Cari")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSearchState.value = false }) {
                    Text("Batal")
                }
            }
        )
    }
    
    Scaffold(
        topBar = { 
            AnimeTopBar(
                onFavoritesClick = onNavigateToFavorites,
                onSearchClick = { showSearchState.value = true }
            ) 
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(padding)
        ) {
            items(uiState.animeList) { anime ->
                AnimeCard(
                    anime = anime,
                    onFavoriteClick = { _, isFavorite ->
                        if (isFavorite) {
                            viewModel.showConfirmDialog(anime)
                        } else {
                            viewModel.toggleFavorite(anime)
                        }
                    }
                )
            }
        }
    }
}
