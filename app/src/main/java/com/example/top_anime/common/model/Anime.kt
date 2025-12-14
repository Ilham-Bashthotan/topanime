package com.example.top_anime.common.model

data class Anime(
    val id: String,
    val title: String,
    val imageUrl: String,
    val type: String,
    val episodes: String,
    val score: String,
    val rank: String,
    val isFavorite: Boolean = false
)
