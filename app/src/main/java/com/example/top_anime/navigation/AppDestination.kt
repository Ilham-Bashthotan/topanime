package com.example.top_anime.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination {
    @Serializable
    data object AnimeList : AppDestination

    @Serializable
    data object Favorites : AppDestination
}
