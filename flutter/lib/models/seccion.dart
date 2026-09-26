import 'package:flutter/material.dart';

class Seccion {
  final String id;
  final String titulo;
  final IconData icono;

  const Seccion({
    required this.id,
    required this.titulo,
    required this.icono,
  });
}

const List<Seccion> secciones = [
  Seccion(id: 'entrada_texto', titulo: 'Entrada de texto', icono: Icons.edit),
  Seccion(id: 'botones', titulo: 'Botones y acciones', icono: Icons.add),
  Seccion(id: 'seleccion', titulo: 'Elementos de selección', icono: Icons.check),
  Seccion(id: 'listas', titulo: 'Listas y colecciones', icono: Icons.list),
  Seccion(id: 'informacion', titulo: 'Información y retroalimentación', icono: Icons.info),
  Seccion(id: 'contenedores', titulo: 'Contenedores y estructura', icono: Icons.home),
];