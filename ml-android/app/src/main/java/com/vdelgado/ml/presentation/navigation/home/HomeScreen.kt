package com.vdelgado.ml.presentation.navigation.home

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
import androidx.paging.compose.collectAsLazyPagingItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: SearchState,
    event: (HomeEvent) -> Unit,
//    navigateToDetails: (MLProductFormatted) -> Unit
) {
    var active by rememberSaveable { mutableStateOf(false) }
    Scaffold(topBar = {
        SearchBar(
            query = state.searchQuery,
            onQueryChange = {
                event(HomeEvent.UpdateSearchQuery(it))
            },
            onSearch = {
                if (active && state.searchQuery.isNotEmpty()) {
                    event(HomeEvent.SearchProduct)
                    event(HomeEvent.UpdateSearchQuery(state.searchQuery))
                }
                active = false
            },
            active = active,
            onActiveChange = {
                active = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar produto"
                )
            },
            placeholder = {
                Text(text = "Buscar produto")
            },
            trailingIcon = {
                if (active) {
                    IconButton(onClick = {
                        if (state.searchQuery.isNotEmpty()) {
                            event(HomeEvent.UpdateSearchQuery(""))
                        } else {
                            active = false
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Buscar produto"
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            //TODO: Inserir items
        }
    }, modifier = Modifier.fillMaxSize()) { innerPadding ->
        state.products?.let { items ->
            val products = items.collectAsLazyPagingItems()
            ProductList(
                products = products, modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            )
        }
    }
}