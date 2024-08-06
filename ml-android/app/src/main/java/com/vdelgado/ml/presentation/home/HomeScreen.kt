package com.vdelgado.ml.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.paging.compose.collectAsLazyPagingItems
import com.vdelgado.ml.presentation.commons.EmptyScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeViewModel.HomeState,
    event: (HomeViewModel.HomeEvent) -> Unit,
    navigateToDetails: (String) -> Unit
) {
    var active by rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = {
            SearchBar(
                query = state.searchQuery,
                onQueryChange = {
                    event(HomeViewModel.HomeEvent.UpdateSearchQuery(it))
                },
                onSearch = {
                    if (state.searchQuery.isNotEmpty()) {
                        event(HomeViewModel.HomeEvent.SearchProduct)
                    }
                    active = false
                    focusManager.clearFocus()
                    keyboardController?.hide()
                },
                active = active,
                onActiveChange = {
                    if (state.searchQuery.isEmpty()) {
                        active = it
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier.testTag("search-icon")
                    )
                },
                placeholder = {
                    Text(text = "Buscar no Mercado Livre")
                },
                trailingIcon = {
                    if (active || state.searchQuery.isNotEmpty()) {
                        IconButton(onClick = {
                            if (state.searchQuery.isNotEmpty()) {
                                event(HomeViewModel.HomeEvent.UpdateSearchQuery(""))
                            } else {
                                active = false
                            }
                        }, modifier.testTag("clear-icon")) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Fechar pesquisa",
                                modifier = modifier.testTag("close-icon")
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        if (state.searchQuery.isEmpty()) {
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