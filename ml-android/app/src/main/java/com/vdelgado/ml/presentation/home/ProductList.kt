package com.vdelgado.ml.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.window.core.layout.WindowWidthSizeClass
import com.vdelgado.ml.data.remote.NoNetworkException
import com.vdelgado.ml.domain.model.MLProductFormatted
import com.vdelgado.ml.presentation.commons.ErrorScreen
import com.vdelgado.ml.presentation.commons.ProgressLoading

@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    products: LazyPagingItems<MLProductFormatted>,
    navigateToDetails: (String) -> Unit
) {
    val handlingResult = handlePagingResults(products)
    if (handlingResult) {
        val isCompatProductList =
            currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass != WindowWidthSizeClass.EXPANDED

        if (isCompatProductList) {
            CompactProductList(modifier = modifier, products = products, navigateToDetails = {
                navigateToDetails(it)
            })
        } else {
            ExpandedProductList(modifier = modifier, products = products, navigateToDetails = {
                navigateToDetails(it)
            })
        }
    }
}

@Composable
fun ExpandedProductList(
    modifier: Modifier = Modifier,
    products: LazyPagingItems<MLProductFormatted>,
    navigateToDetails: (String) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(3)
    ) {
        items(products.itemCount) { index ->
            val product = products[index]
            if (product != null) {
                HorizontalProductItem(
                    title = product.title,
                    originalPrice = product.originalPrice,
                    price = product.price,
                    imageUrl = product.imageUrl,
                    freeShipping = product.freeShipping,
                    installments = product.installments,
                    modifier = Modifier.clickable {
                        navigateToDetails(product.itemId)
                    }
                )
            }
        }
    }
}

@Composable
fun CompactProductList(
    modifier: Modifier = Modifier,
    products: LazyPagingItems<MLProductFormatted>,
    navigateToDetails: (String) -> Unit
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(products.itemCount) { index ->
            val product = products[index]
            if (product != null) {
                VerticalProductItem(
                    title = product.title,
                    originalPrice = product.originalPrice,
                    price = product.price,
                    imageUrl = product.imageUrl,
                    freeShipping = product.freeShipping,
                    installments = product.installments,
                    modifier = Modifier.clickable {
                        navigateToDetails(product.itemId)
                    }
                )
                if (index < products.itemCount)
                    HorizontalDivider(color = Color(0xFFEFEFEF), thickness = 1.dp)
            }
        }
    }
}

@Composable
fun handlePagingResults(
    products: LazyPagingItems<MLProductFormatted>
): Boolean {

    val loadState = products.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ProgressLoading()
            false
        }

        error != null -> {
            when (error.error) {
                is NoNetworkException -> {
                    ErrorScreen(true)
                }

                else -> {
                    ErrorScreen()
                }
            }

            false
        }

        products.itemCount == 0 -> {
            true
        }

        else -> {
            true
        }
    }
}
