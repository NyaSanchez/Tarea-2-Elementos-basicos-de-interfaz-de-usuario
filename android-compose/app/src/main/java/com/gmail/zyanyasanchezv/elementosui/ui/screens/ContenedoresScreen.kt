package com.gmail.zyanyasanchezv.elementosui.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.zyanyasanchezv.elementosui.ui.components.ElementoDemo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenedoresScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Fila, columna, superpuesta
        val disposiciones = listOf("Fila", "Columna", "Superpuesta")
        var disposicionIndice by remember { mutableStateOf(0) }
        ElementoDemo(
            titulo = "Distribución en fila, columna y superpuesta",
            descripcion = "Row coloca los elementos uno junto al otro, Column uno debajo del otro, y Box los superpone en el mismo espacio."
        ) {
            SingleChoiceSegmentedButtonRow {
                disposiciones.forEachIndexed { indice, nombre ->
                    SegmentedButton(
                        selected = disposicionIndice == indice,
                        onClick = { disposicionIndice = indice },
                        shape = SegmentedButtonDefaults.itemShape(index = indice, count = disposiciones.size)
                    ) { Text(nombre) }
                }
            }
            Box(modifier = Modifier.padding(top = 12.dp).height(90.dp).fillMaxWidth()) {
                when (disposicionIndice) {
                    0 -> Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(Modifier.size(40.dp).background(MaterialTheme.colorScheme.primary))
                        Box(Modifier.size(40.dp).background(MaterialTheme.colorScheme.secondary))
                        Box(Modifier.size(40.dp).background(MaterialTheme.colorScheme.tertiary))
                    }
                    1 -> Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Box(Modifier.size(40.dp).background(MaterialTheme.colorScheme.primary))
                        Box(Modifier.size(40.dp).background(MaterialTheme.colorScheme.secondary))
                        Box(Modifier.size(40.dp).background(MaterialTheme.colorScheme.tertiary))
                    }
                    else -> Box {
                        Box(Modifier.size(60.dp).background(MaterialTheme.colorScheme.primary))
                        Box(Modifier.padding(15.dp).size(45.dp).background(MaterialTheme.colorScheme.secondary))
                        Box(Modifier.padding(30.dp).size(30.dp).background(MaterialTheme.colorScheme.tertiary))
                    }
                }
            }
        }

        // 2. Contenedor con desplazamiento vertical
        ElementoDemo(
            titulo = "Contenedor con desplazamiento vertical",
            descripcion = "Cuando el contenido es más alto que el espacio disponible, permite desplazarse para verlo completo."
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                (1..10).forEach { i ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .padding(vertical = 2.dp)
                            .background(
                                if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant
                                else MaterialTheme.colorScheme.surface
                            ),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text("Fila $i", modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
        }

        // 3. Barra superior con acciones
        var mensajeAccion by remember { mutableStateOf("Toca un ícono de la barra") }
        ElementoDemo(
            titulo = "Barra superior con título y acciones",
            descripcion = "Muestra el título de la pantalla y accesos directos a acciones frecuentes, como buscar o abrir más opciones."
        ) {
            TopAppBar(
                title = { Text("Título de ejemplo") },
                actions = {
                    IconButton(onClick = { mensajeAccion = "Tocaste buscar" }) {
                        Icon(Icons.Filled.Search, contentDescription = "Buscar")
                    }
                    IconButton(onClick = { mensajeAccion = "Tocaste más opciones" }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "Más opciones")
                    }
                }
            )
            Text(mensajeAccion, modifier = Modifier.padding(top = 8.dp), style = MaterialTheme.typography.bodySmall)
        }

        // 4. Barra de navegación inferior / menú lateral
        val itemsNav = listOf("Inicio" to Icons.Filled.Home, "Lista" to Icons.Filled.List, "Ajustes" to Icons.Filled.Settings)
        var itemSeleccionado by remember { mutableStateOf(0) }
        ElementoDemo(
            titulo = "Barra de navegación inferior",
            descripcion = "Permite cambiar entre las secciones principales con un toque. Esta app usa además un menú lateral para lo mismo, visible en toda la aplicación."
        ) {
            NavigationBar {
                itemsNav.forEachIndexed { indice, (nombre, icono) ->
                    NavigationBarItem(
                        selected = itemSeleccionado == indice,
                        onClick = { itemSeleccionado = indice },
                        icon = { Icon(icono, contentDescription = nombre) },
                        label = { Text(nombre) }
                    )
                }
            }
            Text(
                "Sección actual: ${itemsNav[itemSeleccionado].first}",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }

        // 5. Pesos proporcionales
        var pesoIzquierdo by remember { mutableStateOf(0.5f) }
        ElementoDemo(
            titulo = "Distribución con pesos proporcionales",
            descripcion = "Los pesos reparten el espacio disponible entre varios elementos de forma proporcional, en vez de usar tamaños fijos."
        ) {
            Text("Ajusta la proporción entre ambos bloques")
            Slider(value = pesoIzquierdo, onValueChange = { pesoIzquierdo = it }, valueRange = 0.1f..0.9f)
            Row(modifier = Modifier.fillMaxWidth().height(60.dp)) {
                Box(
                    modifier = Modifier
                        .weight(pesoIzquierdo)
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.primary)
                )
                Box(
                    modifier = Modifier
                        .weight(1f - pesoIzquierdo)
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.tertiary)
                )
            }
        }
    }
}