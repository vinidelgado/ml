package com.vdelgado.ml.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.vdelgado.ml.presentation.commons.ErrorScreen
import com.vdelgado.ml.ui.theme.MLPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    state: State<ProductDetailViewModel.ProductDetailState>,
    event: (ProductDetailViewModel.ProductDetailEvent) -> Unit,
    onBackClick: () -> Unit,
    itemId: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MLPrimary),
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }

            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        if (state.value.isError) {
            ErrorScreen(state.value.isNetworkError)
        } else {
            state.value.product?.let { product ->
                ProductDetails(modifier = Modifier.padding(innerPadding), product = product)
            }
        }
    }

    event(ProductDetailViewModel.ProductDetailEvent.UpdateProductProduct(itemId))
    event(ProductDetailViewModel.ProductDetailEvent.GetInfoProduct)
}