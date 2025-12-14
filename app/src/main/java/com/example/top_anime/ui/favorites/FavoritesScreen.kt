package com.example.top_anime.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.top_anime.common.model.Anime
import com.example.top_anime.ui.components.AnimeCard

@Composable
fun FavoritesScreen(
    onBackClick: () -> Unit = {}
) {
    val favoriteAnimeList = listOf(
        Anime(
            "11", "Dandadan",
            "https://cdn.myanimelist.net/images/anime/1972/144813.jpg",
            "TV", "12", "8.45", "178"
        ),
        Anime(
            "12", "Sakamoto Days",
            "https://cdn.myanimelist.net/images/anime/1581/144456.jpg",
            "TV", "11", "7.59", "1700"
        )
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
            items(favoriteAnimeList) { anime ->
                AnimeCard(anime)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen()
}
