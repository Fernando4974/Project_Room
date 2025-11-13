package com.example.proyect_compose_2.ui.theme.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.proyect_compose_2.ui.theme.data.productos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(id: Int, onBack: () -> Unit) {

    val productoEncontrado = productos.find { it.id == id }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detalle del Producto") },
                navigationIcon = {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable { onBack() },
                        text = "⬅"
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Producto con ID $id",
                style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
            )

            productoEncontrado?.let { producto ->
                Image(
                    painter = painterResource(id = producto.imageUrl),
                    contentDescription = "Imagen ampliada de ${producto.descripcion}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .size(250.dp)
                        .background(Color.Gray, RoundedCornerShape(16.dp))
                )

                //  DESCRIPCIÓN
                Text(
                    text = "Descripción:",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = producto.descripcion,
                    modifier = Modifier.fillMaxWidth()
                )
            } ?: Text("Error: Producto no encontrado.", color = Color.Red)


            Button(
                onClick = { onBack() },
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text(text = "Volver")
            }
        }
    }
}

