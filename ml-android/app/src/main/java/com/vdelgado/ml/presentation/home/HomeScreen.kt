package com.vdelgado.ml.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.paging.compose.collectAsLazyPagingItems
import com.vdelgado.ml.domain.model.MLProductScreenFormatted
import com.vdelgado.ml.presentation.commons.EmptyScreen
import com.vdelgado.ml.presentation.commons.MLSearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeViewModel.HomeState,
    event: (HomeViewModel.HomeEvent) -> Unit,
    navigateToDetails: (MLProductScreenFormatted) -> Unit
) {
    Scaffold(
        topBar = {
            HomeAppBar(modifier = Modifier.fillMaxWidth(), state = state, event = event)
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
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