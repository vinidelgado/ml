package com.vdelgado.ml.presentation.detail

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vdelgado.ml.R
import com.vdelgado.ml.domain.model.MLProductItem
import com.vdelgado.ml.domain.model.MLProductScreenFormatted
import com.vdelgado.ml.presentation.home.ProductInstallments

@Composable
fun ProductDetails(
    modifier: Modifier = Modifier,
    product: MLProductItem,
    productSelected: MLProductScreenFormatted
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        FirstLineInfomartion(product = product)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            productSelected.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Light,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        CarrouselImages(product = product)

        Spacer(modifier = Modifier.height(36.dp))

        ProductPrice(product = productSelected)

        if (productSelected.installments.isNotEmpty()) {
            ProductInstallments(installments = productSelected.installments)
        }

        Spacer(modifier = Modifier.height(48.dp))

        StockAvailable(product = product)

        val context = LocalContext.current
        val intent = remember {
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(product.permalink)
            )
        }
        Button(
            onClick = {
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4285F4),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = "Comprar",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun ProductPrice(product: MLProductScreenFormatted) {
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
        fontWeight = FontWeight.Light,
        color = Color.Black
    )
}

@Composable
fun StockAvailable(product: MLProductItem) {
    if (product.quantity == 1) {
        Text(
            text = "Último disponível!",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    } else {
        Text(
            text = "Estoque disponível",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(4.dp)),
            verticalArrangement = Arrangement.Center
        ) {
            val quantity = if (product.quantity > 50) {
                "(+50 disponíveis)"
            } else {
                "(${product.quantity} disponíveis)"
            }

            val text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("Quantidade: ")
                }
                withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)) {
                    append("1  ")
                }
                withStyle(style = SpanStyle(color = Color(0xFFB3B3B3))) {
                    append(quantity)
                }
            }

            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
}


@Composable
fun Warranty(product: MLProductItem) {
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
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarrouselImages(modifier: Modifier = Modifier, product: MLProductItem) {
    val pagerState = rememberPagerState(pageCount = {
        product.pictures.size
    })
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF5F5F5))

    ) {
        val currentPage = pagerState.currentPage + 1
        Text(
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 8.dp),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            text = "${currentPage}/${pagerState.pageCount}"
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
    HorizontalPager(
        state = pagerState,
        modifier = modifier,
    ) { page ->
        BannerCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            imageUrl = product.pictures[page]
        )
    }

    Spacer(modifier = Modifier.height(20.dp))
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
                    .size(8.dp)
            )
        }
    }
}

@Composable
fun FirstLineInfomartion(modifier: Modifier = Modifier, product: MLProductItem) {
    Row(
        modifier = modifier
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
            fontSize = 11.sp,
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 2.dp)
        )
    }
}


