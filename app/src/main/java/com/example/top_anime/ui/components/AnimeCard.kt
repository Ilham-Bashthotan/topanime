package com.example.top_anime.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.top_anime.common.model.Anime

@Composable
fun AnimeCard(
    anime: Anime,
    onFavoriteClick: ((String, Boolean) -> Unit)? = null
) {
    Card(
        modifier = Modifier.graphicsLayer { clip = false },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Box {
                AsyncImage(
                    model = anime.imageUrl,
                    contentDescription = anime.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                )

                FavoriteButton(
                    isFavorite = anime.isFavorite,
                    onClick = { 
                        onFavoriteClick?.invoke(anime.id, anime.isFavorite)
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                )
            }

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = anime.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text("Type  ${anime.type}")
                Text("Episodes  ${anime.episodes}")

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Score ${anime.score}", fontWeight = FontWeight.SemiBold)
                    Text("Rank ${anime.rank}", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Preview
@Composable
fun AnimeCardPreview() {
    val anime: Anime = Anime(
        id = "1",
        title = "Naruto",
        imageUrl = "",
        type = "TV",
        episodes = "35",
        score = "9",
        rank = "102"
    )
    AnimeCard(anime)
}
