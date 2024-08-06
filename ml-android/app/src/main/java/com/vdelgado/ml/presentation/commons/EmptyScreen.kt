package com.vdelgado.ml.presentation.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vdelgado.ml.ui.theme.MLTheme

@Composable
fun EmptyScreen(title: String?, message: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val titleText = title ?: "Que tal uma pesquisa para começar ?"
        val messageText = message ?: "Nossa equipe trará os melhores produtos para você."
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = titleText,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = messageText,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
        )

    }
}

@Preview
@Composable
private fun EmptyScreenPreview() {
    MLTheme {
        Box(modifier = Modifier.fillMaxSize().background(Color.White)){
            EmptyScreen(title = "Que tal uma pesquisa para começar ?", message = "Nossa equipe trará os melhores produtos para você.")
        }
    }
}