package com.vdelgado.ml.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vdelgado.ml.domain.model.MLProductScreenFormatted
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
            route = Route.ProductDetailScreen.router,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                ) + fadeIn()
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                ) + fadeOut()
            }
        ) {
            val productDetailViewModel: ProductDetailViewModel = hiltViewModel()
            navController.previousBackStackEntry?.savedStateHandle?.get<MLProductScreenFormatted>("product")
                ?.let { product ->
                    ProductDetailScreen(
                        state = productDetailViewModel.state,
                        event = productDetailViewModel::onEvent,
                        productSelected = product,
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
        }

    }
}

private fun navigateToDetail(
    navController: NavController,
    product: MLProductScreenFormatted
) {
    navController.currentBackStackEntry?.savedStateHandle?.set(
        "product",
        product
    )

    navController.navigate(
        route = Route.ProductDetailScreen.router
    )
}