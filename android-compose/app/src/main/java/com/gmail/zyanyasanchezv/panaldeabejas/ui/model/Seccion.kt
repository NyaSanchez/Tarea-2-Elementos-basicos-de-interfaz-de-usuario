package com.gmail.zyanyasanchezv.panaldeabejas.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

data class Seccion(
    val ruta: String,
    val titulo: String,
    val icono: ImageVector
)

val secciones = listOf(
    Seccion("entrada_texto", "Entrada de texto", Icons.Filled.Edit),
    Seccion("botones", "Botones y acciones", Icons.Filled.Add),
    Seccion("seleccion", "Elementos de selección", Icons.Filled.Check),
    Seccion("listas", "Listas y colecciones", Icons.Filled.List),
    Seccion("informacion", "Información y retroalimentación", Icons.Filled.Info),
    Seccion("contenedores", "Contenedores y estructura", Icons.Filled.Home)
)