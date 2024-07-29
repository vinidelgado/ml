package com.vdelgado.ml.presentation.home

import androidx.compose.ui.test.junit4.createComposeRule
import com.vdelgado.ml.presentation.common.error.ErrorRobot
import org.junit.Rule
import org.junit.Test

class HomeScreenComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun defaultHomeScreen() {
        composeTestRule.setContent {
            HomeScreen(state = HomeViewModel.HomeState(), event = {}, navigateToDetails = {})
        }
        with(HomeRobot(composeTestRule)) {
            checkInitialScreen()
        }
    }
}