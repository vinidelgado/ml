package com.vdelgado.ml.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vdelgado.ml.ui.theme.MLTheme

@Composable
fun HorizontalProductItem(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    title: String,
    originalPrice: String,
    price: String,
    freeShipping: Boolean = false,
    installments: String,
    officialSeller:String?
) {
    Column(
        modifier = modifier
            .fillMaxWidth(0.3f)
            .height(450.dp)
            .padding(8.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(Color.Transparent, shape = RoundedCornerShape(8.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalProductImage(imageUrl)
        }

        Column(
            modifier = Modifier
                .weight(0.6f)
                .padding(start = 12.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Light,
                maxLines = 3,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (originalPrice.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = originalPrice,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF919191),
                    textDecoration = TextDecoration.LineThrough
                )
            }

            Text(
                text = price,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Light,
                color = Color.Black
            )

            if (installments.isNotEmpty()) {
                ProductInstallments(installments = installments)
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

            if (!officialSeller.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                ProductOfficialSeller(officialSeller)
            }

        }
    }
}

@Composable
fun HorizontalProductImage(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .padding(4.dp),
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
private fun ProductItemPreview() {
    MLTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        )
        Column {
            HorizontalProductItem(
                title = "Moto G6 Plus Dual Sim 64 Gb Nimbus 4 Gb Ram",
                originalPrice = "R$ 698,99",
                price = "R$ 750,00",
                imageUrl = null,
                freeShipping = true,
                installments = "em 12x de R$ 69,99 sem juros",
                officialSeller = "Amazon"
            )
        }
    }
}