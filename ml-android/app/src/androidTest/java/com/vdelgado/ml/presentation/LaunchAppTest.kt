package com.vdelgado.ml.presentation

import androidx.compose.ui.test.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vdelgado.ml.presentation.common.error.ErrorRobot
import com.vdelgado.ml.webmock.ErrorDispatcher
import com.vdelgado.ml.webmock.SuccessDispatcher
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class LaunchAppTest : BaseScreenTest() {

    @Test
    fun elementsVisibilityAfterOpeningTheScreen() {
        composeTestRule.apply {
            waitForIdle()
            with(LaunchScreenRobot(composeTestRule)) {
                initialElementsShowed()
            }
        }
    }

    @Test
    fun clearSearchTextAfterTouchInClearIcon() {
        composeTestRule.apply {
            waitForIdle()
            with(LaunchScreenRobot(composeTestRule)) {
                initialElementsShowed()
                fillSearchProduct()
                clearSearchText()
            }
        }
    }

    @Test
    fun searchProductAndLoadSuccessList() {
        mockWebServer.dispatcher = SuccessDispatcher()
        composeTestRule.apply {
            waitForIdle()
            with(LaunchScreenRobot(composeTestRule)) {
                initialElementsShowed()
                fillSearchProduct()
                clickSearchButton()
                waitForIdle()
                loadingShowed()
                productListShowed()
            }
        }
    }

    @Test
    fun searchProductAndLoadErrorList() {
        mockWebServer.dispatcher = ErrorDispatcher()
        composeTestRule.apply {
            waitForIdle()
            with(LaunchScreenRobot(composeTestRule)) {
                initialElementsShowed()
                fillSearchProduct()
                clickSearchButton()
                ErrorRobot(composeTestRule).checkScreen()
            }
        }
    }
}