package com.example.top_anime.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.top_anime.ui.animeList.AnimeListScreen
import com.example.top_anime.ui.favorites.FavoritesScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.AnimeList
    ) {
        composable<AppDestination.AnimeList> {
            AnimeListScreen(
                onNavigateToFavorites = {
                    navController.navigate(AppDestination.Favorites)
                }
            )
        }

        composable<AppDestination.Favorites> {
            FavoritesScreen(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}
