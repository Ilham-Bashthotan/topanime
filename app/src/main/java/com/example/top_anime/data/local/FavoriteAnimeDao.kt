package com.example.top_anime.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteAnimeDao {
    
    @Query("SELECT * FROM favorite_anime")
    fun getAllFavorites(): Flow<List<FavoriteAnimeEntity>>
    
    @Query("SELECT * FROM favorite_anime WHERE id = :id")
    suspend fun getFavoriteById(id: String): FavoriteAnimeEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(anime: FavoriteAnimeEntity)
    
    @Delete
    suspend fun deleteFavorite(anime: FavoriteAnimeEntity)
    
    @Query("DELETE FROM favorite_anime WHERE id = :id")
    suspend fun deleteFavoriteById(id: String)
}
