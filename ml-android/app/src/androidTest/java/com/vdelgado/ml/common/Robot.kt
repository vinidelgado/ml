package com.vdelgado.ml.common

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick

abstract class Robot(private val composeRule: ComposeTestRule) {
    // assertion of buttons and clicking them
    fun clickTextButton(text: String) = composeRule.onNode(hasTextExactly(text)).performClick()

    fun clickIconButton(description: String) = composeRule.onNode(
        hasContentDescription(description).and(
            hasClickAction()
        )
    ).performClick()

    fun assertContentDescriptionText(text: String, ignoreCase: Boolean = false, substring: Boolean = false) =
        composeRule.onNode(hasContentDescription(text, ignoreCase = ignoreCase, substring = substring))
            .assertExists()

    fun goBack() = clickIconButton("Back button") // uses the same description in all app

    fun assertIconButton(description: String) =
        composeRule.onNode(hasContentDescription(description).and(hasClickAction())).assertExists()

    fun assertTextButton(text: String) =
        composeRule.onNode(hasText(text).and(hasClickAction())).assertExists()

    fun assertTag(tag:String) =
        composeRule.onNodeWithTag(tag,useUnmergedTree = true).assertExists()

    fun assertImage(description: String) =
        composeRule.onNode(hasContentDescription(description)).assertExists()

    // text assertions
    fun assertText(text: String, ignoreCase: Boolean = false, substring: Boolean = false) =
        composeRule.onNode(hasText(text, ignoreCase = ignoreCase, substring = substring))
            .assertExists()

    fun assertDoesNotExistText(
        text: String, ignoreCase: Boolean = false, substring: Boolean = false
    ) = composeRule.onNode(hasText(text, ignoreCase = ignoreCase, substring = substring))
        .assertDoesNotExist()

    fun assertTextBesideImage(text: String, description: String) {
        composeRule.onNode(
            hasText(text).and(
                hasAnySibling(hasContentDescription(description))
            )
        ).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitFor(matcher: SemanticsMatcher) = composeRule.waitUntilExactlyOneExists(matcher)
}
