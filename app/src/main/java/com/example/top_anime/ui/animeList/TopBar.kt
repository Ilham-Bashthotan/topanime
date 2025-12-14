package com.example.top_anime.ui.animeList

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeTopBar(
    onFavoritesClick: () -> Unit = {},
    onSearchClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = "MotionAnimeList",
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
            IconButton(onClick = onFavoritesClick) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorite")
            }
        }
    )
}
