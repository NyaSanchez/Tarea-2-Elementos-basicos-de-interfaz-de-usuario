package com.gmail.zyanyasanchezv.elementosui.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gmail.zyanyasanchezv.elementosui.R
import com.gmail.zyanyasanchezv.elementosui.ui.components.ElementoDemo
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformacionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Textos con distintos estilos, tamaños y énfasis
        val estilosTipografia = listOf(
            "Título" to MaterialTheme.typography.headlineSmall,
            "Subtítulo" to MaterialTheme.typography.titleMedium,
            "Cuerpo" to MaterialTheme.typography.bodyLarge,
            "Etiqueta" to MaterialTheme.typography.labelSmall
        )
        var estiloIndice by remember { mutableStateOf(0) }
        ElementoDemo(
            titulo = "Textos con distintos estilos, tamaños y énfasis",
            descripcion = "La tipografía cambia según su función: título, cuerpo o etiqueta. También puede resaltarse con negrita, cursiva o subrayado."
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                estilosTipografia.forEachIndexed { indice, (nombre, _) ->
                    OutlinedButton(onClick = { estiloIndice = indice }) { Text(nombre) }
                }
            }
            Text(
                text = "Texto de ejemplo",
                style = estilosTipografia[estiloIndice].second,
                modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
            )
            HorizontalDivider()
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Text("Texto en negrita", fontWeight = FontWeight.Bold)
                Text("Texto en cursiva", fontStyle = FontStyle.Italic)
                Text("Texto subrayado", textDecoration = TextDecoration.Underline)
            }
        }

        // 2. Imagen local e imagen desde URL, con distintos modos de escalado
        val modosEscala = listOf(
            "Recortar" to ContentScale.Crop,
            "Ajustar" to ContentScale.Fit,
            "Rellenar" to ContentScale.FillBounds
        )
        var modoIndice by remember { mutableStateOf(0) }
        ElementoDemo(
            titulo = "Imagen local e imagen desde URL",
            descripcion = "La primera imagen viene guardada dentro de la app; la segunda se descarga de internet. Cambia el modo de escalado para ver cómo se ajustan."
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                modosEscala.forEachIndexed { indice, (nombre, _) ->
                    OutlinedButton(onClick = { modoIndice = indice }) { Text(nombre) }
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(top = 12.dp)) {
                Column {
                    Text("Local", style = MaterialTheme.typography.labelSmall)
                    Image(
                        painter = painterResource(id = R.drawable.imagen_local),
                        contentDescription = "Imagen guardada en la app",
                        contentScale = modosEscala[modoIndice].second,
                        modifier = Modifier.size(120.dp)
                    )
                }
                Column {
                    Text("Desde URL", style = MaterialTheme.typography.labelSmall)
                    AsyncImage(
                        model = "https://picsum.photos/300",
                        contentDescription = "Imagen cargada desde internet",
                        contentScale = modosEscala[modoIndice].second,
                        modifier = Modifier.size(120.dp)
                    )
                }
            }
        }

        // 3. Indicadores de progreso
        var progreso by remember { mutableStateOf(0.4f) }
        var mostrarIndeterminado by remember { mutableStateOf(false) }
        ElementoDemo(
            titulo = "Indicadores de progreso",
            descripcion = "El modo determinado muestra un porcentaje exacto de avance. El indeterminado indica que una tarea está en curso sin un valor definido."
        ) {
            Text("Progreso: ${(progreso * 100).toInt()}%")
            Slider(value = progreso, onValueChange = { progreso = it })
            LinearProgressIndicator(progress = { progreso }, modifier = Modifier.fillMaxWidth())
            CircularProgressIndicator(progress = { progreso }, modifier = Modifier.padding(top = 12.dp))
            Button(
                onClick = { mostrarIndeterminado = !mostrarIndeterminado },
                modifier = Modifier.padding(top = 12.dp)
            ) {
                Text(if (mostrarIndeterminado) "Ocultar indeterminado" else "Mostrar indeterminado")
            }
            if (mostrarIndeterminado) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
                CircularProgressIndicator(modifier = Modifier.padding(top = 8.dp))
            }
        }

        // 4. Toast y snackbar
        val context = LocalContext.current
        val estadoSnackbar = remember { SnackbarHostState() }
        val scopeSnackbar = rememberCoroutineScope()
        ElementoDemo(
            titulo = "Mensaje emergente (toast) y mensaje con acción (snackbar)",
            descripcion = "El toast es un aviso breve que desaparece solo. El snackbar puede incluir una acción, como deshacer."
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = {
                    Toast.makeText(context, "Este es un mensaje breve", Toast.LENGTH_SHORT).show()
                }) { Text("Mostrar toast") }
                Button(onClick = {
                    scopeSnackbar.launch {
                        estadoSnackbar.showSnackbar(message = "Elemento eliminado", actionLabel = "Deshacer")
                    }
                }) { Text("Mostrar snackbar") }
            }
            SnackbarHost(hostState = estadoSnackbar, modifier = Modifier.padding(top = 8.dp))
        }

        // 5. Diálogo de confirmación
        var mostrarDialogo by remember { mutableStateOf(false) }
        var resultadoDialogo by remember { mutableStateOf("Sin respuesta aún") }
        ElementoDemo(
            titulo = "Diálogo de confirmación",
            descripcion = "Interrumpe el flujo para pedir que el usuario confirme o cancele una acción antes de ejecutarla."
        ) {
            Button(onClick = { mostrarDialogo = true }) { Text("Eliminar cuenta") }
            Text(resultadoDialogo, modifier = Modifier.padding(top = 8.dp), style = MaterialTheme.typography.bodySmall)
        }
        if (mostrarDialogo) {
            AlertDialog(
                onDismissRequest = { mostrarDialogo = false },
                title = { Text("¿Confirmar acción?") },
                text = { Text("Esta acción no se puede deshacer. ¿Deseas continuar?") },
                confirmButton = {
                    TextButton(onClick = { resultadoDialogo = "Confirmaste la acción"; mostrarDialogo = false }) {
                        Text("Confirmar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { resultadoDialogo = "Cancelaste la acción"; mostrarDialogo = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }

        // 6. Hoja inferior
        var mostrarHoja by remember { mutableStateOf(false) }
        val estadoHoja = rememberModalBottomSheetState()
        ElementoDemo(
            titulo = "Hoja inferior (bottom sheet)",
            descripcion = "Panel que se desliza desde abajo mostrando opciones adicionales sin abandonar la pantalla actual."
        ) {
            Button(onClick = { mostrarHoja = true }) { Text("Abrir opciones") }
        }
        if (mostrarHoja) {
            ModalBottomSheet(onDismissRequest = { mostrarHoja = false }, sheetState = estadoHoja) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Opciones", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "Este panel aparece desde abajo y se cierra deslizando hacia abajo o tocando fuera.",
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }

        // 7. Tarjeta, separador y distintivo numérico
        var notificaciones by remember { mutableStateOf(3) }
        ElementoDemo(
            titulo = "Tarjeta, separador y distintivo numérico (badge)",
            descripcion = "La tarjeta agrupa contenido relacionado, el separador divide secciones y el distintivo resalta una cantidad, como notificaciones sin leer."
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "Bandeja de entrada",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.weight(1f)
                        )
                        BadgedBox(badge = {
                            if (notificaciones > 0) Badge { Text("$notificaciones") }
                        }) {
                            Icon(Icons.Filled.Notifications, contentDescription = "Notificaciones")
                        }
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = { notificaciones++ }) { Text("Agregar") }
                        OutlinedButton(onClick = { if (notificaciones > 0) notificaciones-- }) { Text("Marcar leída") }
                    }
                }
            }
        }
    }
}