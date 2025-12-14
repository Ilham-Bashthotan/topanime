package com.example.top_anime.di

import com.example.top_anime.data.remote.service.AnimeConfig
import com.example.top_anime.data.remote.service.AnimeService
import org.koin.dsl.module

val networkModule = module {
	single<AnimeService> { AnimeConfig.getAnimeService() }
}
