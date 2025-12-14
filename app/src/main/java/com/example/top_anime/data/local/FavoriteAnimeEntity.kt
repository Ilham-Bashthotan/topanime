package com.example.top_anime.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.top_anime.common.model.Anime

@Entity(tableName = "favorite_anime")
data class FavoriteAnimeEntity(
    @PrimaryKey val id: String,
    val title: String,
    val imageUrl: String,
    val type: String,
    val episodes: String,
    val score: String,
    val rank: String
)

fun FavoriteAnimeEntity.toDomain(): Anime = Anime(
    id = id,
    title = title,
    imageUrl = imageUrl,
    type = type,
    episodes = episodes,
    score = score,
    rank = rank,
    isFavorite = true
)

fun Anime.toEntity(): FavoriteAnimeEntity = FavoriteAnimeEntity(
    id = id,
    title = title,
    imageUrl = imageUrl,
    type = type,
    episodes = episodes,
    score = score,
    rank = rank
)
