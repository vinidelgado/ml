package com.vdelgado.ml.presentation

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.vdelgado.ml.MainActivity
import com.vdelgado.ml.presentation.navigation.NavGraph
import com.vdelgado.ml.presentation.navigation.Route
import dagger.hilt.android.testing.HiltAndroidRule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule

open class BaseScreenTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    val mockWebServer by lazy { MockWebServer() }

    @Before
    fun setup() {
        hiltRule.inject()
        mockWebServer.start(port = 8080)
        composeTestRule.activity.setContent {
            NavGraph(startDestination = Route.HomeScreen.router)
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


}