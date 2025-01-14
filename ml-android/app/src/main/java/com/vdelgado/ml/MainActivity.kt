package com.vdelgado.ml

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.vdelgado.ml.presentation.navigation.NavGraph
import com.vdelgado.ml.presentation.navigation.Route
import com.vdelgado.ml.ui.theme.MLPrimaryDark
import com.vdelgado.ml.ui.theme.MLTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = MLPrimaryDark.toArgb(),
                darkScrim = MLPrimaryDark.toArgb()
            )
        )
        setContent {
            enableEdgeToEdge()
            MLTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    NavGraph(startDestination = Route.HomeScreen.router)
                }
            }
        }
    }
}





