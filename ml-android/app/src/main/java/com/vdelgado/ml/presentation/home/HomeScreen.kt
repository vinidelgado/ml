package com.vdelgado.ml.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.vdelgado.ml.domain.model.MLProductScreenFormatted
import com.vdelgado.ml.presentation.commons.EmptyScreen
import com.vdelgado.ml.presentation.commons.MLSearchBar
import com.vdelgado.ml.ui.theme.MLPrimary
import com.vdelgado.ml.ui.theme.MLPrimaryDark

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeViewModel.HomeState,
    event: (HomeViewModel.HomeEvent) -> Unit,
    navigateToDetails: (MLProductScreenFormatted) -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            HomeAppBar(modifier = Modifier.fillMaxWidth(), state = state, event = event)
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            MLPrimaryDark,
                            MLPrimary
                        )
                    )
                )
        )
        if (state.searchQuery.isEmpty() && state.products == null) {
            EmptyScreen(title = null, message = null)
        } else {
            state.products?.let { items ->
                val products = items.collectAsLazyPagingItems()
                ProductList(
                    products = products, modifier = Modifier
                        .fillMaxWidth()
                        .testTag("product-list")
                        .padding(innerPadding),
                    navigateToDetails = {
                        navigateToDetails(it)
                    }
                )
            }
        }
    }
}

@Composable
private fun HomeAppBar(
    modifier: Modifier = Modifier,
    state: HomeViewModel.HomeState,
    event: (HomeViewModel.HomeEvent) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    MLSearchBar(
        modifier = modifier,
        onSearchClick = {
            event(HomeViewModel.HomeEvent.SearchProduct)
            keyboardController?.hide()
        },
        onQueryChange = {
            event(HomeViewModel.HomeEvent.UpdateSearchQuery(it))
        },
        state = state
    )
}