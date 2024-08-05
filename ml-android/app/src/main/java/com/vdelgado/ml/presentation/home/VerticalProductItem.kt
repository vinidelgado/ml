package com.vdelgado.ml.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vdelgado.ml.ui.theme.MLTheme

@Composable
fun VerticalProductItem(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    title: String,
    originalPrice: String,
    price: String,
    freeShipping: Boolean = false,
    installments: String,
) {
    Row(
        modifier = modifier
            .height(200.dp)
            .padding(8.dp), verticalAlignment = Alignment.Top
    ) {
        Column(
            Modifier
                .fillMaxWidth(0.4f)
                .fillMaxHeight()
                .background(Color.Transparent, shape = RoundedCornerShape(8.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ProductImage(imageUrl)
        }

        Column(
            modifier = Modifier
                .weight(0.6f)
                .padding(start = 12.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Light,
                maxLines = 3,
                fontSize = 16.sp
            )
            if (originalPrice.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
            }

            Text(
                text = originalPrice,
                style = MaterialTheme.typography.bodyMedium,
                textDecoration = TextDecoration.LineThrough
            )
            Text(
                text = price,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )

            if (installments.isNotEmpty()) {
                val textColor =
                    if (installments.contains("sem juros")) Color(0xFF009E5E) else Color.Black
                Text(
                    text = installments,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light,
                    color = textColor
                )
            }

            if (freeShipping) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Frete grátis",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF38AA74)
                )
            }
        }
    }
}

@Composable
fun ProductImage(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}

@Preview
@Composable
private fun ProductItemPreview() {
    MLTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        )
        Column {
            VerticalProductItem(
                title = "Moto G6 Plus Dual Sim 64 Gb Nimbus 4 Gb Ram",
                originalPrice = "R$ 698,99",
                price = "R$ 750,00",
                imageUrl = null,
                freeShipping = true,
                installments = "em 12x de R$ 69,99 sem juros"
            )
        }
    }
}