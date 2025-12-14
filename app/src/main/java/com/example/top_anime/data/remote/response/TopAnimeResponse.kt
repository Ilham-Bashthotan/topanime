package com.example.top_anime.data.remote.response

import com.google.gson.annotations.SerializedName

data class TopAnimeResponse(
    @SerializedName("data") val data: List<AnimeItem>
)

data class AnimeItem(
    @SerializedName("mal_id") val id: Long,
    @SerializedName("title") val title: String?,
    @SerializedName("images") val images: Images?,
    @SerializedName("type") val type: String?,
    @SerializedName("episodes") val episodes: Int?,
    @SerializedName("score") val score: Double?,
    @SerializedName("rank") val rank: Int?
)

data class Images(
    @SerializedName("jpg") val jpg: Jpg?
)

data class Jpg(
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("large_image_url") val largeImageUrl: String?
)
