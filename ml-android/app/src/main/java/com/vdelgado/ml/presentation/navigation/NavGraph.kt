package com.vdelgado.ml.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vdelgado.ml.presentation.navigation.home.HomeScreen
import com.vdelgado.ml.presentation.navigation.home.HomeViewModel

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(
            route = Route.HomeScreen.router
        ) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(state = homeViewModel.state.value, event = homeViewModel::onEvent)
        }
//        composable(
//            route = Route.HomeScreen.router
//        ) {
//            val homeViewModel: HomeViewModel = hiltViewModel()
//            HomeScreen(state = homeViewModel.state.value, event = homeViewModel::onEvent)
//        }

    }
}