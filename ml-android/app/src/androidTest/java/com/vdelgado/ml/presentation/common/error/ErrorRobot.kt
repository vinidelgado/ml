package com.vdelgado.ml.presentation.common.error

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import com.vdelgado.ml.common.Robot

class ErrorRobot(private val composeRule: ComposeTestRule) : Robot(composeRule) {

    @OptIn(ExperimentalTestApi::class)
    fun checkScreen() {
        composeRule.waitUntilAtLeastOneExists(
            matcher = hasText("Parece que tivemos um erro!"),
            timeoutMillis = 1000L
        )
        assertImage("Erro")
        assertText("Parece que tivemos um erro!")
        assertText("Nossa equipe já está trabalhando para resolver o problema, tente novamente dentro de alguns minutos")
    }

    fun checkNetworkErrorScreen() {
        assertImage("Erro")
        assertText("Parece que você está sem conexão com a internet")
        assertText("Verifique sua conexão para continuar navegando e comprandos os melhores produtos")
    }

}