package com.vdelgado.ml.presentation.home

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.vdelgado.ml.common.Robot
import org.junit.Rule

class HomeRobot(composeRule: ComposeTestRule): Robot(composeRule) {
    @get:Rule
    val composeTestRule = createComposeRule()


    fun checkInitialScreen(){
        assertText("Buscar no Mercado Livre")
    }
}