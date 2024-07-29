package com.vdelgado.ml.presentation.common.progress_loading

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.vdelgado.ml.common.Robot

class ProgressLoadingRobot(composeRule: ComposeTestRule): Robot(composeRule)  {
    fun checkScreen(){
        assertImage("Carregando as informações")
    }
}