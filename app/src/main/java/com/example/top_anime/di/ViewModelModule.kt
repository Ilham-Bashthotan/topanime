package com.example.top_anime.di

import com.example.top_anime.ui.animeList.AnimeListViewModel
import com.example.top_anime.ui.favorites.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AnimeListViewModel(get()) }
    viewModel { FavoritesViewModel(get()) }
}
