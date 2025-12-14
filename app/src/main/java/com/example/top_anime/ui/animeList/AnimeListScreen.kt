package com.example.top_anime.ui.animeList

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
fun AnimeListScreen() {
    val animeList = listOf(
        Anime(
            "1", "Sousou no Frieren",
            "https://cdn.myanimelist.net/images/anime/1015/138006.jpg",
            "TV", "28", "9.28", "1"
        ),
        Anime(
            "2", "Chainsaw Man Movie: Reze-hen",
            "https://cdn.myanimelist.net/images/anime/1763/150638.jpg",
            "Movie", "1", "9.17", "2"
        ),
        Anime(
            "3", "Fullmetal Alchemist: Brotherhood",
            "https://cdn.myanimelist.net/images/anime/1223/96541.jpg",
            "TV", "64", "9.10", "3"
        ),
        Anime(
            "4", "Steins;Gate",
            "https://cdn.myanimelist.net/images/anime/5/73199.jpg",
            "TV", "24", "9.08", "4"
        )
    )
    Scaffold(
        topBar = { AnimeTopBar() }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(padding)
        ) {
            items(animeList) { anime ->
                AnimeCard(anime)
            }
        }
    }
}

// preview
@Preview(showBackground = true)
@Composable
fun AnimeListScreenPreview() {
    AnimeListScreen()
}
