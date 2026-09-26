package com.gmail.zyanyasanchezv.elementosui.ui.navigation

import com.gmail.zyanyasanchezv.elementosui.ui.screens.ListasColeccionesScreen
import com.gmail.zyanyasanchezv.elementosui.ui.screens.SeleccionScreen
import com.gmail.zyanyasanchezv.elementosui.ui.screens.BotonesAccionesScreen
import com.gmail.zyanyasanchezv.elementosui.ui.screens.EntradaTextoScreen
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gmail.zyanyasanchezv.elementosui.ui.model.secciones
import com.gmail.zyanyasanchezv.elementosui.ui.screens.HomeScreen
import com.gmail.zyanyasanchezv.elementosui.ui.screens.SeccionPlaceholderScreen
import kotlinx.coroutines.launch
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Elementos de la Interfaz",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text("Inicio") },
                    selected = currentRoute == "inicio",
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                    onClick = {
                        navController.navigate("inicio") {
                            popUpTo("inicio") { inclusive = true }
                        }
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                secciones.forEach { seccion ->
                    NavigationDrawerItem(
                        label = { Text(seccion.titulo) },
                        selected = currentRoute == seccion.ruta,
                        icon = { Icon(seccion.icono, contentDescription = null) },
                        onClick = {
                            navController.navigate(seccion.ruta)
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Elementos de la Interfaz") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Abrir menú")
                        }
                    }
                )
            }
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = "inicio",
                modifier = Modifier.padding(padding)
            ) {
                composable("inicio") { HomeScreen(navController) }
                composable("entrada_texto") { EntradaTextoScreen() }
                composable("botones") { BotonesAccionesScreen() }
                composable("seleccion") { SeleccionScreen() }
                composable("listas") { ListasColeccionesScreen() }
                secciones.filter {
                    it.ruta !in listOf("entrada_texto", "botones", "seleccion", "listas")
                }.forEach { seccion ->
                    composable(seccion.ruta) { SeccionPlaceholderScreen(seccion.titulo) }
                }
            }
        }
    }
}