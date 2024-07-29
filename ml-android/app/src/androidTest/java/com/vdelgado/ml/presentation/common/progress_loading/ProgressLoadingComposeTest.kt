package com.vdelgado.ml.presentation.common.progress_loading

import androidx.compose.ui.test.junit4.createComposeRule
import com.vdelgado.ml.presentation.commons.ProgressLoading
import org.junit.Rule
import org.junit.Test

class ProgressLoadingComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun defaultErrorScreenTest() {
        composeTestRule.setContent {
            ProgressLoading()
        }
        with(ProgressLoadingRobot(composeTestRule)) {
            checkScreen()
        }
    }
}