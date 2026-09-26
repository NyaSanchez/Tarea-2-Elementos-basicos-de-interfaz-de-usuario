package com.gmail.zyanyasanchezv.elementosui.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.zyanyasanchezv.elementosui.ui.components.ElementoDemo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BotonesAccionesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Relleno, contorno, texto
        var mensaje by remember { mutableStateOf("Aún no has presionado ningún botón") }
        ElementoDemo(
            titulo = "Botón relleno, con contorno y de texto",
            descripcion = "Tres estilos de énfasis distinto: relleno para la acción principal, contorno para una acción secundaria y de texto para una acción de bajo énfasis."
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { mensaje = "Presionaste el botón relleno" }) { Text("Relleno") }
                OutlinedButton(onClick = { mensaje = "Presionaste el botón con contorno" }) { Text("Contorno") }
                TextButton(onClick = { mensaje = "Presionaste el botón de texto" }) { Text("Texto") }
            }
            Text(
                text = mensaje,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // 2. Botón con ícono
        var favorito by remember { mutableStateOf(false) }
        ElementoDemo(
            titulo = "Botón con ícono",
            descripcion = "Un botón puede mostrar solo un ícono, o un ícono junto con texto para reforzar su significado."
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                IconButton(onClick = { favorito = !favorito }) {
                    Icon(
                        imageVector = if (favorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Marcar como favorito"
                    )
                }
                Button(onClick = { favorito = !favorito }) {
                    Icon(
                        imageVector = if (favorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(if (favorito) "En favoritos" else "Agregar a favoritos")
                }
            }
        }

        // 3. FAB normal y extendido
        var contador by remember { mutableStateOf(0) }
        ElementoDemo(
            titulo = "Botón de acción flotante (FAB)",
            descripcion = "Resalta la acción principal de una pantalla. Su versión extendida agrega una etiqueta de texto junto al ícono."
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                FloatingActionButton(onClick = { contador++ }) {
                    Icon(Icons.Filled.Add, contentDescription = "Agregar")
                }
                ExtendedFloatingActionButton(
                    onClick = { contador++ },
                    icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                    text = { Text("Agregar elemento") }
                )
            }
            Text(
                text = "Elementos agregados: $contador",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // 4. Alternancia / selector segmentado
        val opcionesVista = listOf("Lista", "Cuadrícula")
        var vistaSeleccionada by remember { mutableStateOf(0) }
        ElementoDemo(
            titulo = "Selector segmentado",
            descripcion = "Permite elegir una opción entre varias mutuamente excluyentes, mostradas como botones unidos."
        ) {
            SingleChoiceSegmentedButtonRow {
                opcionesVista.forEachIndexed { index, etiqueta ->
                    SegmentedButton(
                        selected = vistaSeleccionada == index,
                        onClick = { vistaSeleccionada = index },
                        shape = SegmentedButtonDefaults.itemShape(index = index, count = opcionesVista.size)
                    ) {
                        Text(etiqueta)
                    }
                }
            }
            Text(
                text = "Vista seleccionada: ${opcionesVista[vistaSeleccionada]}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // 5. Deshabilitado y en carga
        var cargando by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        ElementoDemo(
            titulo = "Botón deshabilitado y en estado de carga",
            descripcion = "Un botón deshabilitado no responde a toques. Uno en carga muestra un indicador mientras procesa la acción."
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = {}, enabled = false) { Text("Deshabilitado") }
                Button(
                    onClick = {
                        scope.launch {
                            cargando = true
                            delay(1500)
                            cargando = false
                        }
                    },
                    enabled = !cargando
                ) {
                    if (cargando) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text("Guardar")
                    }
                }
            }
        }
    }
}