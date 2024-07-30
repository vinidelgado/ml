package com.vdelgado.ml.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
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
        composeTestRule.apply {
            waitForIdle()
            with(LaunchScreenRobot(composeTestRule)) {
                initialElementsShowed()
                fillSearchProduct()
                mainClock.advanceTimeBy(400L)
                mainClock.autoAdvance = true
                clickSearchButton()
                loadingShowed()
            }
        }
    }

}