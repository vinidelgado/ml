package com.vdelgado.ml.presentation

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import com.vdelgado.ml.common.Robot

class LaunchScreenRobot(val composeRule: ComposeTestRule) : Robot(composeRule) {

    private val searchTextField by lazy { assertText("Buscar no Mercado Livre") }
    private val clearIcon by lazy { assertTag("clear-icon") }
    private val searchEditText by lazy { assertText("Samsung") } //Fixed product to test, cannot change the product
    private val loading by lazy { assertContentDescriptionText("Carregando as informações") }
    private val productList by lazy { assertTag("product-list") }

    fun initialElementsShowed() {
        searchTextField.assertIsDisplayed()
    }

    fun fillSearchProduct() {
        searchTextField.performClick()
        searchTextField.performTextInput("Samsung")
    }

    fun clearSearchText() {
        clearIcon.isDisplayed()
        clearIcon.performClick()
    }

    fun clickSearchButton() {
        searchEditText.performImeAction()
    }

    fun loadingShowed() {
        loading.assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    fun productListShowed() {
        composeRule.waitUntilExactlyOneExists(hasTestTag("product-list"),1000L)
        productList.assertIsDisplayed()
    }

}