package com.vdelgado.ml

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.vdelgado.ml.domain.model.MLProductFormatted
import com.vdelgado.ml.presentation.HomeViewModel
import com.vdelgado.ml.ui.theme.MLTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: HomeViewModel = hiltViewModel()
            var text by rememberSaveable { mutableStateOf("") }
            var active by rememberSaveable { mutableStateOf(false) }
            MLTheme {
                Scaffold(topBar = {
                    SearchBar(
                        query = text,
                        onQueryChange = {
                            text = it
                        },
                        onSearch = {
                            if (active && text.isNotEmpty()) {
                                viewModel.searchProductPager(text)
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
                                    if (text.isNotEmpty()) {
                                        text = ""
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
                    viewModel.state.value.products?.let { items ->
                        val products = items.collectAsLazyPagingItems()
                        if (products.loadState.refresh is LoadState.Loading) {
                            ProgressLoading()
                        } else if (products.loadState.refresh is LoadState.Error) {
                            Text("Erro")
                        } else {
                            ProductList(
                                products = products, modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressLoading() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
fun ProductList(modifier: Modifier = Modifier, products: LazyPagingItems<MLProductFormatted>) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(products.itemCount) { index ->
            val product = products[index]
            if (product != null) {
                ProductItem(
                    modifier = Modifier.fillMaxHeight(0.3f),
                    title = product.title,
                    originalPrice = product.originalPrice,
                    price = product.price,
                    imageUrl = product.imageUrl,
                    freeShipping = product.freeShipping,
                    installments = product.installments
                )
                if (index < products.itemCount)
                    HorizontalDivider(color = Color(0xFFEFEFEF), thickness = 1.dp)
            }
        }
    }
}

