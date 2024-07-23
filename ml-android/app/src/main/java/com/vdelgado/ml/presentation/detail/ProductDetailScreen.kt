package com.vdelgado.ml.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProductDetailScreen(
    state: State<DetailState>,
    event: (DetailItemEvent) -> Unit,
    itemId:String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Panela De Pressão Clock 3l Antiaderente Revestida Cinza Cor Preto",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Best Seller",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "14th in Caçarolas e Caldeirões Clock",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(16.dp))

//        Image(
//            painter = painterResource(R.drawable.pressure_cooker),
//            contentDescription = "Pressure Cooker",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//                .background(Color.Gray)
//        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Color: Black",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "R$ 178,99",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "in 12x R$ 17,40",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(50)
        ) {
            Text(text = "View Payment Methods")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Free delivery tomorrow",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Green
        )

        TextButton(
            onClick = { /*TODO*/ }
        ) {
            Text(text = "More delivery options")
        }

        Text(
            text = "Pick up for free from tomorrow at a Mercado Livre agency",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Green
        )

        Text(
            text = "Buying within the next 11h 2min",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        state.value.product?.let {
            Text(text = it.title)
        }

        event(DetailItemEvent.UpdateProductItem(itemId))
        event(DetailItemEvent.GetInfoProduct)


    }
}