package com.gmail.zyanyasanchezv.elementosui.ui.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.gmail.zyanyasanchezv.elementosui.ui.components.ElementoDemo
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeleccionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Casilla con estado indeterminado
        var opcion1 by remember { mutableStateOf(false) }
        var opcion2 by remember { mutableStateOf(false) }
        var opcion3 by remember { mutableStateOf(false) }
        val estados = listOf(opcion1, opcion2, opcion3)
        val estadoPadre = when {
            estados.all { it } -> ToggleableState.On
            estados.none { it } -> ToggleableState.Off
            else -> ToggleableState.Indeterminate
        }
        ElementoDemo(
            titulo = "Casilla de verificación con estado indeterminado",
            descripcion = "La casilla principal muestra un estado intermedio cuando solo algunas de sus opciones hijas están marcadas."
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TriStateCheckbox(
                    state = estadoPadre,
                    onClick = {
                        val nuevoValor = estadoPadre != ToggleableState.On
                        opcion1 = nuevoValor; opcion2 = nuevoValor; opcion3 = nuevoValor
                    }
                )
                Text("Seleccionar todas")
            }
            Column(modifier = Modifier.padding(start = 32.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = opcion1, onCheckedChange = { opcion1 = it })
                    Text("Opción 1")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = opcion2, onCheckedChange = { opcion2 = it })
                    Text("Opción 2")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = opcion3, onCheckedChange = { opcion3 = it })
                    Text("Opción 3")
                }
            }
        }

        // 2. Botones de opción (radio)
        val tallas = listOf("S", "M", "L", "XL")
        var tallaSeleccionada by remember { mutableStateOf(tallas[1]) }
        ElementoDemo(
            titulo = "Grupo de botones de opción",
            descripcion = "Permite elegir una sola opción entre varias; seleccionar una desmarca automáticamente las demás."
        ) {
            Column {
                tallas.forEach { talla ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.selectable(
                            selected = tallaSeleccionada == talla,
                            onClick = { tallaSeleccionada = talla }
                        )
                    ) {
                        RadioButton(selected = tallaSeleccionada == talla, onClick = { tallaSeleccionada = talla })
                        Text(talla, modifier = Modifier.padding(start = 4.dp))
                    }
                }
            }
        }

        // 3. Interruptor
        var notificaciones by remember { mutableStateOf(true) }
        ElementoDemo(
            titulo = "Interruptor (switch)",
            descripcion = "Activa o desactiva una opción de forma inmediata, sin necesidad de confirmar."
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(checked = notificaciones, onCheckedChange = { notificaciones = it })
                Spacer(Modifier.width(8.dp))
                Text(if (notificaciones) "Notificaciones activadas" else "Notificaciones desactivadas")
            }
        }

        // 4. Sliders
        var volumen by remember { mutableStateOf(50f) }
        var rangoPrecio by remember { mutableStateOf(20f..80f) }
        ElementoDemo(
            titulo = "Deslizador de valor único y de rango",
            descripcion = "El primero elige un solo valor dentro de un rango; el segundo elige un intervalo con dos extremos."
        ) {
            Text("Volumen: ${volumen.toInt()}")
            Slider(value = volumen, onValueChange = { volumen = it }, valueRange = 0f..100f)
            Spacer(Modifier.width(4.dp))
            Text("Rango de precio: ${rangoPrecio.start.toInt()} - ${rangoPrecio.endInclusive.toInt()}")
            RangeSlider(value = rangoPrecio, onValueChange = { rangoPrecio = it }, valueRange = 0f..100f)
        }

        // 5. Lista desplegable de selección
        val categorias = listOf("Categoría 1", "Categoría 2", "Categoría 3")
        var categoriaSeleccionada by remember { mutableStateOf(categorias.first()) }
        var expandido by remember { mutableStateOf(false) }
        ElementoDemo(
            titulo = "Lista desplegable de selección",
            descripcion = "Muestra una lista de opciones fijas al pulsar el botón; se elige una y la lista se cierra."
        ) {
            Box {
                OutlinedButton(onClick = { expandido = true }) {
                    Text(categoriaSeleccionada)
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Abrir lista")
                }
                DropdownMenu(expanded = expandido, onDismissRequest = { expandido = false }) {
                    categorias.forEach { categoria ->
                        DropdownMenuItem(
                            text = { Text(categoria) },
                            onClick = { categoriaSeleccionada = categoria; expandido = false }
                        )
                    }
                }
            }
        }

        // 6. Selector de fecha y hora
        val context = LocalContext.current
        var fechaSeleccionada by remember { mutableStateOf("Sin seleccionar") }
        var horaSeleccionada by remember { mutableStateOf("Sin seleccionar") }
        ElementoDemo(
            titulo = "Selector de fecha y selector de hora",
            descripcion = "Abren un diálogo nativo de Android para elegir una fecha o una hora específica."
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = {
                    val calendario = Calendar.getInstance()
                    DatePickerDialog(
                        context,
                        { _, anio, mes, dia -> fechaSeleccionada = "%02d/%02d/%d".format(dia, mes + 1, anio) },
                        calendario.get(Calendar.YEAR),
                        calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }) { Text("Elegir fecha") }

                OutlinedButton(onClick = {
                    val calendario = Calendar.getInstance()
                    TimePickerDialog(
                        context,
                        { _, hora, minuto -> horaSeleccionada = "%02d:%02d".format(hora, minuto) },
                        calendario.get(Calendar.HOUR_OF_DAY),
                        calendario.get(Calendar.MINUTE),
                        true
                    ).show()
                }) { Text("Elegir hora") }
            }
            Text(
                text = "Fecha: $fechaSeleccionada   Hora: $horaSeleccionada",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // 7. Chips de filtro
        val etiquetas = listOf("Nuevo", "Popular", "Oferta", "Recomendado")
        var seleccionadas by remember { mutableStateOf(setOf<String>()) }
        ElementoDemo(
            titulo = "Chips de filtro seleccionables",
            descripcion = "Cada chip se activa o desactiva de forma independiente; se puede seleccionar más de uno a la vez."
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                etiquetas.forEach { etiqueta ->
                    FilterChip(
                        selected = etiqueta in seleccionadas,
                        onClick = {
                            seleccionadas = if (etiqueta in seleccionadas) seleccionadas - etiqueta else seleccionadas + etiqueta
                        },
                        label = { Text(etiqueta) }
                    )
                }
            }
            Text(
                text = "Seleccionados: ${if (seleccionadas.isEmpty()) "ninguno" else seleccionadas.joinToString()}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}