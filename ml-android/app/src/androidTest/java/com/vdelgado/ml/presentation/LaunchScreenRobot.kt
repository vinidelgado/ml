package com.vdelgado.ml.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import com.vdelgado.ml.common.Robot

class LaunchScreenRobot(composeRule: ComposeTestRule) : Robot(composeRule) {

    private val searchIcon by lazy { assertTag("search-icon") }
    private val searchTextField by lazy { assertText("Buscar no Mercado Livre") }
    private val clearIcon by lazy { assertTag("clear-icon") }
    private val closeIcon by lazy { assertTag("close-icon") }
    private val searchEditText by lazy { assertText("Samsung") } //Fixed product to test, cannot change the product
    private val loading by lazy { assertContentDescriptionText("Carregando as informações") }

    fun initialElementsShowed() {
        searchIcon.assertIsDisplayed()
        searchTextField.assertIsDisplayed()
    }

    fun fillSearchProduct() {
        searchTextField.performClick()
        closeIcon.isDisplayed()
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

}