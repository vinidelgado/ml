package com.vdelgado.ml.presentation.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vdelgado.ml.R

@Composable
fun ErrorScreen(isNetworkError: Boolean = false) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(76.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_error_icon),
            contentDescription = "Erro"
        )
        Spacer(modifier = Modifier.height(8.dp))
        var title = "Parece que tivemos um erro!"
        var message =
            "Nossa equipe já está trabalhando para resolver o problema, tente novamente dentro de alguns minutos"
        if (isNetworkError) {
            title = "Parece que você está sem conexão com a internet"
            message =
                "Verifique sua conexão para continuar navegando e comprandos os melhores produtos"
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
        )

    }
}