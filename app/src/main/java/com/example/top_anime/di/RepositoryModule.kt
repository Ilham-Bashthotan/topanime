package com.example.top_anime.di

import com.example.top_anime.data.repository.AnimeRepository
import com.example.top_anime.data.repository.AnimeRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<AnimeRepository> { AnimeRepositoryImpl() }
}
