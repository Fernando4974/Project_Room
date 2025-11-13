package com.example.proyect_compose_2.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyect_compose_2.ui.theme.data.Producto // Asegúrate de que tu clase Producto sea accesible
import com.example.proyect_compose_2.ui.theme.data.productos // Tu lista de productos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigationToDetails: (Int) -> Unit,
    onNavigationToSettings: () -> Unit // Aunque no lo usemos visualmente ahora, lo mantenemos
) {
    // Definimos un color de fondo oscuro para que coincida con la imagen
    val DarkBackground = Color(0xFF1E1E2C) // Un gris oscuro azulado
    val CardBackground = Color(0xFF2B2B3D) // Un poco más claro para las tarjetas

    Scaffold(
        containerColor = DarkBackground, // Establece el color de fondo para todo el Scaffold
        topBar = {
            TopAppBar(
                title = { Text(text = "SHIRTS", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back */ }) {
                        Icon(Icons.Filled.ArrowBack, "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle search */ }) {
                        Icon(Icons.Filled.Search, "Search", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBackground, // Color de la barra superior
                    titleContentColor = Color.White // Color del texto del título
                )
            )
        },
        bottomBar = {
            BottomNavigationBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(DarkBackground) // Asegura que el Column también tenga el fondo oscuro
        ) {
            // Sección de Filtros y Ordenamiento
            FilterAndSortSection()

            // Cuadrícula de Productos
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Dos columnas como en la imagen
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(productos) { producto ->
                    ProductCard(producto = producto, onClick = { onNavigationToDetails(producto.id) })
                }
            }
        }
    }
}

@Composable
fun FilterAndSortSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent) // Fondo transparente, el padre maneja el color
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón DEPARTMENT
        Row(
            modifier = Modifier
                .clickable { /* Handle department click */ }
                .padding(vertical = 4.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "DEPARTMENT", color = Color.LightGray, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Icon(Icons.Filled.KeyboardArrowDown, "Expand", tint = Color.LightGray, modifier = Modifier.size(18.dp))
        }

        // Botón FILTER & SORT
        Row(
            modifier = Modifier
                .clickable { /* Handle filter & sort click */ }
                .padding(vertical = 4.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "FILTER & SORT", color = Color.LightGray, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Icon(Icons.Filled.Sort, "Sort", tint = Color.LightGray, modifier = Modifier.size(18.dp))
        }
    }
    // Una pequeña línea divisoria
    Divider(color = Color.DarkGray, thickness = 0.5.dp, modifier = Modifier.padding(horizontal = 16.dp))
}

@Composable
fun ProductCard(producto: Producto, onClick: () -> Unit) {
    val CardBackground = Color(0xFF2B2B3D) // Color de fondo para las tarjetas

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min) // Para que la altura se ajuste al contenido
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp) // Altura fija para la imagen, similar a la referencia
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(Color.Gray) // Un placeholder mientras carga o para fondos
            ) {
                Image(
                    painter = painterResource(id = producto.imageUrl),
                    contentDescription = "Imagen de ${producto.descripcion}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Icono de corazón en la esquina
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = "Add to favorites",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .size(24.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = producto.descripcion,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1 // Evita que el texto se salga de la tarjeta
                )
                Text(
                    text = "$${producto.price}", // Suponiendo que tienes una propiedad 'price'
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    val DarkBackground = Color(0xFF1E1E2C)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBackground)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavigationItem(icon = Icons.Filled.List, label = "", onClick = { /* Tab 1 */ })
        BottomNavigationItem(icon = Icons.Filled.Search, label = "", onClick = { /* Tab 2 */ })
        BottomNavigationItem(icon = Icons.Filled.FavoriteBorder, label = "", onClick = { /* Tab 3 */ })
        BottomNavigationItem(icon = Icons.Filled.Settings, label = "", onClick = { /* Tab 4 */ })
    }
}

@Composable
fun BottomNavigationItem(icon: ImageVector, label: String, onClick: () -> Unit) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = Color.White, modifier = Modifier.size(24.dp))
        if (label.isNotEmpty()) {
            Text(text = label, color = Color.White, fontSize = 10.sp)
        }
    }
}

