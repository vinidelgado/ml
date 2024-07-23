package com.vdelgado.ml.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vdelgado.ml.presentation.detail.DetailItemEvent
import com.vdelgado.ml.presentation.detail.ProductDetailScreen
import com.vdelgado.ml.presentation.detail.ProductDetailViewModel
import com.vdelgado.ml.presentation.home.HomeScreen
import com.vdelgado.ml.presentation.home.HomeViewModel

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(
            route = Route.HomeScreen.router
        ) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                state = homeViewModel.state.value,
                event = homeViewModel::onEvent,
                navigateToDetails = {
                    navigateToDetail(navController = navController, it)
                })
        }
        composable(
            route = Route.ProductDetailScreen.router
        ) {
            val productDetailViewModel: ProductDetailViewModel = hiltViewModel()
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("itemId")
                ?.let { itemId ->
                    ProductDetailScreen(
                        state = productDetailViewModel.state,
                        event = productDetailViewModel::onEvent,
                        itemId = itemId
                    )
                }
        }

    }
}

private fun navigateToDetail(
    navController: NavController,
    itemId: String
) {
    navController.currentBackStackEntry?.savedStateHandle?.set(
        "itemId",
        itemId
    )

    navController.navigate(
        route = Route.ProductDetailScreen.router
    )
}