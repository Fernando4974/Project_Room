package com.example.proyect_compose_2.ui.theme.data

import com.example.proyect_compose_2.R


// ESTRUCTURA
data class Producto(
    val id: Int,
    val descripcion: String,
    val imageUrl: Int,
    val price: Double
)

// LISTA
val productos = listOf(

    Producto(101, "Silla ergonómica de oficina y malla transpirable", R.drawable.silla,4455.7),
    Producto(102, "Mesa de centro moderna con tapa de cristal", R.drawable.msea,8787.3),
    Producto(103, "Lámpara de pie ajustable de diseño minimalista", R.drawable.lampara,326.3),
    Producto(104, "Sofá modular de tres puestos en color gris oscuro", R.drawable.sofa,123123.98),
    Producto(105, "Estantería flotante de madera de roble macizo", R.drawable.estanteria, 458.100),
    Producto(106, "Espejo de pared con marco metálico negro", R.drawable.espejo, 20000.500),
    Producto(107, "Silla ergonómica de oficina y malla transpirable", R.drawable.silla,4455.7),
    Producto(108, "Mesa de centro moderna con tapa de cristal", R.drawable.msea,8787.3),
    Producto(109, "Lámpara de pie ajustable de diseño minimalista", R.drawable.lampara,326.3),
    Producto(110, "Sofá modular de tres puestos en color gris oscuro", R.drawable.sofa,123123.98),
    Producto(111, "Estantería flotante de madera de roble macizo", R.drawable.estanteria, 458.100),
    Producto(112, "Espejo de pared con marco metálico negro", R.drawable.espejo, 20000.500)

)