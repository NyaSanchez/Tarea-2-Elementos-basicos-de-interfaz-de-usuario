package com.gmail.zyanyasanchezv.elementosui.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gmail.zyanyasanchezv.elementosui.ui.components.ElementoDemo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListasColeccionesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Lista vertical con 15+ elementos
        val elementosLista = (1..20).map { "Elemento $it" }
        ElementoDemo(
            titulo = "Lista vertical",
            descripcion = "Muestra una colección larga de elementos que se desplaza verticalmente; solo renderiza lo visible en pantalla."
        ) {
            LazyColumn(modifier = Modifier.height(220.dp)) {
                items(elementosLista) { nombre ->
                    Text(nombre, modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp))
                    HorizontalDivider()
                }
            }
        }

        // 2. Cuadrícula
        val elementosGrid = (1..12).map { "Elemento $it" }
        ElementoDemo(
            titulo = "Cuadrícula de elementos",
            descripcion = "Organiza los elementos en columnas, útil cuando el contenido es visual y se aprovecha mejor el ancho de pantalla."
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.height(220.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(elementosGrid) { nombre ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(60.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(nombre, textAlign = TextAlign.Center, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }

        // 3. Encabezados de sección con 2 tipos distintos
        val frutas = listOf("Manzana", "Plátano", "Naranja", "Uva", "Fresa")
        val verduras = listOf("Zanahoria", "Brócoli", "Espinaca", "Papa")
        ElementoDemo(
            titulo = "Lista con encabezados de sección",
            descripcion = "Agrupa elementos de distinto tipo bajo un encabezado que identifica a qué categoría pertenecen."
        ) {
            LazyColumn(modifier = Modifier.height(240.dp)) {
                item { Text("Frutas", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(vertical = 8.dp)) }
                items(frutas) { Text("• $it", modifier = Modifier.padding(vertical = 4.dp)) }
                item { HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp)) }
                item { Text("Verduras", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(vertical = 8.dp)) }
                items(verduras) { Text("• $it", modifier = Modifier.padding(vertical = 4.dp)) }
            }
        }

        // 4. Selección de elemento -> detalle
        val productos = listOf(
            "Producto A" to "Este es el detalle del producto A: disponible, envío en 2 días.",
            "Producto B" to "Detalle del producto B: agotado temporalmente.",
            "Producto C" to "Detalle del producto C: en oferta esta semana.",
            "Producto D" to "Detalle del producto D: nuevo ingreso al catálogo."
        )
        var productoDetalle by remember { mutableStateOf<Pair<String, String>?>(null) }
        ElementoDemo(
            titulo = "Selección de elemento con detalle",
            descripcion = "Al tocar un elemento de la lista, se muestra su información completa en un diálogo."
        ) {
            LazyColumn(modifier = Modifier.height(180.dp)) {
                items(productos) { (nombre, descripcion) ->
                    Text(
                        text = nombre,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { productoDetalle = nombre to descripcion }
                            .padding(vertical = 12.dp)
                    )
                    HorizontalDivider()
                }
            }
        }
        if (productoDetalle != null) {
            AlertDialog(
                onDismissRequest = { productoDetalle = null },
                confirmButton = {
                    TextButton(onClick = { productoDetalle = null }) { Text("Cerrar") }
                },
                title = { Text(productoDetalle!!.first) },
                text = { Text(productoDetalle!!.second) }
            )
        }

        // 5. Deslizar para eliminar + Estado vacío
        val listaOriginal = remember { (1..5).map { "Tarea $it" } }
        val lista = remember { mutableStateListOf(*listaOriginal.toTypedArray()) }
        ElementoDemo(
            titulo = "Deslizar para eliminar y estado vacío",
            descripcion = "Desliza un elemento hacia un lado para eliminarlo. Cuando ya no quedan elementos, se muestra un mensaje y una ilustración."
        ) {
            if (lista.isEmpty()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth().padding(24.dp)
                ) {
                    Icon(
                        Icons.Filled.Inbox,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        "No hay elementos",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        "Elimina elementos deslizando, o reinicia para verlos de nuevo.",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = { lista.addAll(listaOriginal) },
                        modifier = Modifier.padding(top = 12.dp)
                    ) { Text("Reiniciar") }
                }
            } else {
                LazyColumn(modifier = Modifier.height(220.dp)) {
                    items(lista, key = { it }) { nombre ->
                        val estadoDismiss = rememberSwipeToDismissBoxState(
                            confirmValueChange = { valor ->
                                if (valor != SwipeToDismissBoxValue.Settled) {
                                    lista.remove(nombre)
                                    true
                                } else false
                            }
                        )
                        SwipeToDismissBox(
                            state = estadoDismiss,
                            backgroundContent = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(MaterialTheme.colorScheme.errorContainer)
                                        .padding(horizontal = 20.dp),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Icon(
                                        Icons.Filled.Delete,
                                        contentDescription = "Eliminar",
                                        tint = MaterialTheme.colorScheme.onErrorContainer
                                    )
                                }
                            }
                        ) {
                            Card(modifier = Modifier.fillMaxWidth()) {
                                Text(nombre, modifier = Modifier.padding(16.dp))
                            }
                        }
                    }
                }
            }
        }

        // 6. Pull to refresh
        val scope = rememberCoroutineScope()
        var refrescando by remember { mutableStateOf(false) }
        var actualizaciones by remember { mutableStateOf(0) }
        val estadoRefresco = rememberPullToRefreshState()
        ElementoDemo(
            titulo = "Actualizar arrastrando hacia abajo",
            descripcion = "Al arrastrar la lista hacia abajo desde el principio, se dispara una recarga de datos."
        ) {
            PullToRefreshBox(
                isRefreshing = refrescando,
                onRefresh = {
                    scope.launch {
                        refrescando = true
                        delay(1200)
                        actualizaciones++
                        refrescando = false
                    }
                },
                state = estadoRefresco,
                modifier = Modifier.height(220.dp)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        Text(
                            "Actualizaciones recibidas: $actualizaciones",
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                    items((1..10).toList()) { i ->
                        Text(
                            "Registro $i",
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }

        // 7. Pestañas con contenido deslizable
        val titulosTabs = listOf("Tab 1", "Tab 2", "Tab 3")
        val estadoPager = rememberPagerState(pageCount = { titulosTabs.size })
        val scopeTabs = rememberCoroutineScope()
        ElementoDemo(
            titulo = "Pestañas con contenido deslizable",
            descripcion = "Cada pestaña muestra un contenido distinto; se puede cambiar tocando la pestaña o deslizando el contenido."
        ) {
            Column {
                TabRow(selectedTabIndex = estadoPager.currentPage) {
                    titulosTabs.forEachIndexed { index, titulo ->
                        Tab(
                            selected = estadoPager.currentPage == index,
                            onClick = { scopeTabs.launch { estadoPager.animateScrollToPage(index) } },
                            text = { Text(titulo) }
                        )
                    }
                }
                HorizontalPager(
                    state = estadoPager,
                    modifier = Modifier.height(120.dp).fillMaxWidth()
                ) { pagina ->
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Contenido de ${titulosTabs[pagina]}")
                    }
                }
            }
        }
    }
}