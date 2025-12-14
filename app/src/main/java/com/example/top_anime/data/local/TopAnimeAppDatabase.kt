package com.example.top_anime.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteAnimeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TopAnimeAppDatabase : RoomDatabase() {
    abstract fun favoriteAnimeDao(): FavoriteAnimeDao
}
