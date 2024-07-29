package com.vdelgado.ml.presentation.common.error

import androidx.compose.ui.test.junit4.createComposeRule
import com.vdelgado.ml.presentation.commons.ErrorScreen
import org.junit.Rule
import org.junit.Test

class ErrorScreenComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun defaultErrorScreenTest() {
        composeTestRule.setContent {
            ErrorScreen()
        }
        with(ErrorRobot(composeTestRule)) {
            checkScreen()
        }
    }

    @Test
    fun networkErrorScreenTest() {
        composeTestRule.setContent {
            ErrorScreen(isNetworkError = true)
        }
        with(ErrorRobot(composeTestRule)) {
            checkNetworkErrorScreen()
        }
    }


}