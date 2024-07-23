package com.vdelgado.ml.presentation.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vdelgado.ml.R
import com.vdelgado.ml.domain.model.MLProductItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductDetails(modifier: Modifier = Modifier, product: MLProductItem) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        FirstLineInfomartion(product = product)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            product.title,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        val pagerState = rememberPagerState(pageCount = {
            product.pictures.size
        })
        HorizontalPager(
            state = pagerState,
            modifier = modifier,
        ) { page ->
            BannerCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                imageUrl = product.pictures[page]
            )
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color(0xFF368BFE) else Color(0xFFD7D7D7)
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (product.warranty.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(4.dp)),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = product.warranty,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (product.originalPrice.isNotEmpty()) {
            Text(
                text = product.originalPrice,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Light,
                color = Color.Gray,
                textDecoration = TextDecoration.LineThrough
            )
        }

        Text(
            text = product.price,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (product.quantity == 1) {
            Text(
                text = "Último disponível!",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* TODO: Implementar ação de compra */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4285F4),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = "Comprar",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun FirstLineInfomartion(modifier: Modifier = Modifier, product: MLProductItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val productCondition = when (product.condition) {
            "new" -> {
                stringResource(id = R.string.condition_new)
            }

            "used" -> {
                stringResource(id = R.string.condition_used)
            }

            else -> {
                stringResource(id = R.string.condition_not_specified)
            }

        }

        Text(
            text = productCondition,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


