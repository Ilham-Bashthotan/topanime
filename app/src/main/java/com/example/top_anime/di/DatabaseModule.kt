package com.example.top_anime.di

import androidx.room.Room
import com.example.top_anime.data.local.FavoriteAnimeDao
import com.example.top_anime.data.local.TopAnimeAppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            TopAnimeAppDatabase::class.java,
            "top_anime_database"
        ).build()
    }
    
    single<FavoriteAnimeDao> {
        get<TopAnimeAppDatabase>().favoriteAnimeDao()
    }
}
