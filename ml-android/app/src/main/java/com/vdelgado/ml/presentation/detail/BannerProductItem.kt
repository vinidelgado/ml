package com.vdelgado.ml.presentation.detail

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun BannerCard(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    Row(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = modifier,
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
    }
}