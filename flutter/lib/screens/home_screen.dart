import 'entrada_texto_screen.dart';
import 'package:flutter/material.dart';
import '../models/seccion.dart';
import 'placeholder_screen.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  int _indiceActual = 0; // 0 = inicio

  String get _tituloActual {
    if (_indiceActual == 0) return 'Elementos de la Interfaz';
    return secciones[_indiceActual - 1].titulo;
  }

  Widget get _contenidoActual {
    if (_indiceActual == 0) return _buildInicio();
    if (_indiceActual == 1) return const EntradaTextoScreen();
    return SeccionPlaceholderScreen(titulo: secciones[_indiceActual - 1].titulo);
  }

  void _seleccionar(int indice) {
    setState(() => _indiceActual = indice);
    Navigator.pop(context);
  }

  Widget _buildInicio() {
    return GridView.count(
      crossAxisCount: 2,
      padding: const EdgeInsets.all(16),
      crossAxisSpacing: 12,
      mainAxisSpacing: 12,
      childAspectRatio: 1.3,
      children: secciones.map((seccion) {
        final indice = secciones.indexOf(seccion) + 1;
        return Card(
          child: InkWell(
            onTap: () => setState(() => _indiceActual = indice),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Icon(seccion.icono, size: 32),
                const SizedBox(height: 8),
                Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 8),
                  child: Text(
                    seccion.titulo,
                    textAlign: TextAlign.center,
                    style: Theme.of(context).textTheme.bodyMedium,
                  ),
                ),
              ],
            ),
          ),
        );
      }).toList(),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(_tituloActual)),
      drawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: [
            DrawerHeader(
              child: Text(
                'Elementos Básicos de la Interfaz de Usuario',
                style: Theme.of(context).textTheme.titleMedium,
              ),
            ),
            ListTile(
              leading: const Icon(Icons.home),
              title: const Text('Inicio'),
              selected: _indiceActual == 0,
              onTap: () => _seleccionar(0),
            ),
            for (int i = 0; i < secciones.length; i++)
              ListTile(
                leading: Icon(secciones[i].icono),
                title: Text(secciones[i].titulo),
                selected: _indiceActual == i + 1,
                onTap: () => _seleccionar(i + 1),
              ),
          ],
        ),
      ),
      body: _contenidoActual,
    );
  }
}