package com.vdelgado.ml.presentation.navigation

sealed class Route(val router:String){
    data object HomeScreen:Route("homeScreen")
    data object ProductDetailScreen:Route("detailScreen")

}