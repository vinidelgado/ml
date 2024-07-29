package com.vdelgado.ml.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val MLColorScheme = lightColorScheme(
    primary = MLPrimary,
    onPrimary = MLPrimaryDark,
    background = Color.White
)

@Composable
fun MLTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MLColorScheme,
        typography = Typography,
        content = content
    )
}


//@Composable
//fun MLTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    dynamicColor: Boolean = false,
//    content: @Composable () -> Unit
//) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}