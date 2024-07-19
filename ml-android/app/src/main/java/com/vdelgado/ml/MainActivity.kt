package com.vdelgado.ml

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.vdelgado.ml.domain.model.MLProduct
import com.vdelgado.ml.presentation.HomeViewModel
import com.vdelgado.ml.ui.theme.MLTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: HomeViewModel = hiltViewModel()
            val items = viewModel.searchProductPager("Poco X3 NFC").collectAsLazyPagingItems()
//            viewModel.searchProduct("Poco X3 NFC")
            MLTheme {
                Timber.d("MainActivity")
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Button(
                        onClick = {

                        },
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Text("Submit Request")
                    }

                    ProductList(products = items, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
fun ProductList(modifier: Modifier = Modifier, products: LazyPagingItems<MLProduct>) {
    LazyColumn(modifier = modifier) {
        items(products.itemCount) { index ->
            val product = products[index]
            if (product != null) {
                Text(text = product.title ?: "")
            }
        }
    }
}