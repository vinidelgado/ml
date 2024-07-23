package com.vdelgado.ml.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.vdelgado.ml.data.remote.data.MLProductItemResponse
import com.vdelgado.ml.presentation.commons.ErrorScreen
import com.vdelgado.ml.ui.theme.MLPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    state: State<DetailState>,
    event: (DetailItemEvent) -> Unit,
    onBackClick: () -> Unit,
    itemId: String,
    modifier: Modifier = Modifier
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

    event(DetailItemEvent.UpdateProductItem(itemId))
    event(DetailItemEvent.GetInfoProduct)
}

@Composable
fun CarrouselImage(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
    ) {

    }
}