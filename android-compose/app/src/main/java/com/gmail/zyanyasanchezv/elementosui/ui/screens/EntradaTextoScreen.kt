package com.gmail.zyanyasanchezv.elementosui.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.gmail.zyanyasanchezv.elementosui.ui.components.ElementoDemo
import com.gmail.zyanyasanchezv.elementosui.ui.model.secciones

@Composable
fun EntradaTextoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Campo simple
        var nombre by remember { mutableStateOf("") }
        ElementoDemo(
            titulo = "Campo de texto simple",
            descripcion = "Captura texto libre. La etiqueta indica qué se espera y se mueve arriba del campo al escribir."
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // 2. Validación
        var usuario by remember { mutableStateOf("") }
        val usuarioInvalido = usuario.isNotEmpty() && usuario.length < 4
        ElementoDemo(
            titulo = "Campo con validación",
            descripcion = "Valida el contenido mientras se escribe y muestra un mensaje de error visible si no cumple la regla."
        ) {
            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Usuario") },
                isError = usuarioInvalido,
                supportingText = {
                    if (usuarioInvalido) Text("Debe tener al menos 4 caracteres")
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // 3. Contraseña
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        ElementoDemo(
            titulo = "Campo de contraseña",
            descripcion = "Oculta el texto escrito por defecto. El ícono permite mostrarlo u ocultarlo."
        ) {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // 4. Tipos de teclado
        var edad by remember { mutableStateOf("") }
        var correo by remember { mutableStateOf("") }
        var telefono by remember { mutableStateOf("") }
        ElementoDemo(
            titulo = "Campos con distintos tipos de teclado",
            descripcion = "El teclado que aparece cambia según el tipo de dato esperado: numérico, correo o teléfono."
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = edad,
                    onValueChange = { edad = it },
                    label = { Text("Edad (numérico)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo electrónico") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // 5. Multilínea
        var comentarios by remember { mutableStateOf("") }
        ElementoDemo(
            titulo = "Campo multilínea",
            descripcion = "Permite escribir varias líneas de texto, útil para comentarios o descripciones largas."
        ) {
            OutlinedTextField(
                value = comentarios,
                onValueChange = { comentarios = it },
                label = { Text("Comentarios") },
                minLines = 3,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // 6. Autocompletar / desplegable
        val opciones = listOf("Opción A", "Opción B", "Opción C", "Opción D")
        var opcionSeleccionada by remember { mutableStateOf(opciones.first()) }
        var menuExpandido by remember { mutableStateOf(false) }
        ElementoDemo(
            titulo = "Campo con sugerencias (desplegable)",
            descripcion = "Muestra una lista de opciones predefinidas para que el usuario elija una en vez de escribirla."
        ) {
            OutlinedTextField(
                value = opcionSeleccionada,
                onValueChange = {},
                readOnly = true,
                label = { Text("Selecciona una opción") },
                trailingIcon = {
                    IconButton(onClick = { menuExpandido = true }) {
                        Icon(Icons.Filled.ArrowDropDown, contentDescription = "Abrir opciones")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            DropdownMenu(expanded = menuExpandido, onDismissRequest = { menuExpandido = false }) {
                opciones.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            opcionSeleccionada = opcion
                            menuExpandido = false
                        }
                    )
                }
            }
        }

        // 7. Barra de búsqueda
        var busqueda by remember { mutableStateOf("") }
        val resultados = secciones.filter { it.titulo.contains(busqueda, ignoreCase = true) }
        ElementoDemo(
            titulo = "Barra de búsqueda",
            descripcion = "Filtra una lista en tiempo real conforme el usuario escribe. Aquí filtra las secciones del catálogo."
        ) {
            OutlinedTextField(
                value = busqueda,
                onValueChange = { busqueda = it },
                placeholder = { Text("Buscar sección...") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )
            Column(modifier = Modifier.padding(top = 8.dp)) {
                if (resultados.isEmpty()) {
                    Text(
                        text = "Sin resultados",
                        style = MaterialTheme.typography.bodySmall
                    )
                } else {
                    resultados.forEach { seccion ->
                        Text(text = "• ${seccion.titulo}", modifier = Modifier.padding(vertical = 2.dp))
                    }
                }
            }
        }
    }
}