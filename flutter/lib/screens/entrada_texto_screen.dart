import 'package:flutter/material.dart';

import '../models/datos_compartidos.dart';
import '../widgets/elemento_demo.dart';

class EntradaTextoScreen extends StatefulWidget {
  const EntradaTextoScreen({super.key});

  @override
  State<EntradaTextoScreen> createState() =>
      _EntradaTextoScreenState();
}

class _EntradaTextoScreenState
    extends State<EntradaTextoScreen> {
  final _nombreController = TextEditingController();
  final _usuarioController = TextEditingController();
  final _passwordController = TextEditingController();
  final _edadController = TextEditingController();
  final _correoController = TextEditingController();
  final _telefonoController = TextEditingController();
  final _comentariosController = TextEditingController();
  final _busquedaController = TextEditingController();

  bool _passwordVisible = false;

  String _opcionSeleccionada = 'Opción A';

  String _mensajeConexion =
      'Escribe un nombre para enviarlo a la Sección 4.';

  final List<String> _opciones = [
    'Opción A',
    'Opción B',
    'Opción C',
    'Opción D',
  ];

  final List<String> _todasLasSecciones = [
    'Entrada de texto',
    'Botones y acciones',
    'Elementos de selección',
    'Listas y colecciones',
    'Información y retroalimentación',
    'Contenedores y estructura',
  ];

  @override
  void dispose() {
    _nombreController.dispose();
    _usuarioController.dispose();
    _passwordController.dispose();
    _edadController.dispose();
    _correoController.dispose();
    _telefonoController.dispose();
    _comentariosController.dispose();
    _busquedaController.dispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final usuarioInvalido =
        _usuarioController.text.isNotEmpty &&
            _usuarioController.text.length < 4;

    final resultadosBusqueda = _todasLasSecciones
        .where(
          (seccion) => seccion
          .toLowerCase()
          .contains(
        _busquedaController.text.toLowerCase(),
      ),
    )
        .toList();

    return SingleChildScrollView(
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment:
        CrossAxisAlignment.stretch,
        children: [

          // 1. CAMPO DE TEXTO SIMPLE
          ElementoDemo(
            titulo: 'Campo de texto simple',
            descripcion:
            'Captura texto libre. La etiqueta indica qué información '
                'se espera del usuario. Este ejemplo también conecta '
                'la Sección 1 con la Sección 4.',
            contenido: Column(
              crossAxisAlignment:
              CrossAxisAlignment.stretch,
              children: [

                TextField(
                  controller: _nombreController,
                  decoration:
                  const InputDecoration(
                    labelText: 'Nombre',
                    border:
                    OutlineInputBorder(),
                  ),
                ),

                const SizedBox(height: 10),

                FilledButton.icon(
                  onPressed: () {
                    final nombre =
                    _nombreController.text.trim();

                    if (nombre.isEmpty) {
                      setState(() {
                        _mensajeConexion =
                        'Primero escribe un nombre.';
                      });

                      return;
                    }

                    DatosCompartidos.agregar(
                      nombre,
                    );

                    setState(() {
                      _mensajeConexion =
                      '"$nombre" se agregó a la lista de la Sección 4.';
                    });

                    _nombreController.clear();
                  },
                  icon: const Icon(
                    Icons.playlist_add,
                  ),
                  label: const Text(
                    'Agregar a Listas y colecciones',
                  ),
                ),

                const SizedBox(height: 8),

                Text(
                  _mensajeConexion,
                  style: Theme.of(context)
                      .textTheme
                      .bodySmall,
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 2. VALIDACIÓN
          ElementoDemo(
            titulo: 'Campo con validación',
            descripcion:
            'Valida el contenido mientras se escribe y muestra '
                'un mensaje de error visible si no cumple la regla.',
            contenido: TextField(
              controller: _usuarioController,
              onChanged: (_) {
                setState(() {});
              },
              decoration: InputDecoration(
                labelText: 'Usuario',
                border:
                const OutlineInputBorder(),
                errorText: usuarioInvalido
                    ? 'Debe tener al menos 4 caracteres'
                    : null,
              ),
            ),
          ),

          const SizedBox(height: 16),

          // 3. CONTRASEÑA
          ElementoDemo(
            titulo: 'Campo de contraseña',
            descripcion:
            'Oculta el texto escrito por defecto. '
                'El ícono permite mostrar u ocultar '
                'el contenido de la contraseña.',
            contenido: TextField(
              controller: _passwordController,
              obscureText: !_passwordVisible,
              decoration: InputDecoration(
                labelText: 'Contraseña',
                border:
                const OutlineInputBorder(),
                suffixIcon: IconButton(
                  icon: Icon(
                    _passwordVisible
                        ? Icons.visibility_off
                        : Icons.visibility,
                  ),
                  onPressed: () {
                    setState(() {
                      _passwordVisible =
                      !_passwordVisible;
                    });
                  },
                ),
              ),
            ),
          ),

          const SizedBox(height: 16),

          // 4. TIPOS DE TECLADO
          ElementoDemo(
            titulo:
            'Campos con distintos tipos de teclado',
            descripcion:
            'El teclado mostrado cambia según el tipo de dato '
                'esperado. Se incluyen ejemplos numérico, '
                'correo electrónico y teléfono.',
            contenido: Column(
              children: [

                TextField(
                  controller: _edadController,
                  keyboardType:
                  TextInputType.number,
                  decoration:
                  const InputDecoration(
                    labelText:
                    'Edad (numérico)',
                    border:
                    OutlineInputBorder(),
                  ),
                ),

                const SizedBox(height: 12),

                TextField(
                  controller:
                  _correoController,
                  keyboardType:
                  TextInputType.emailAddress,
                  decoration:
                  const InputDecoration(
                    labelText:
                    'Correo electrónico',
                    border:
                    OutlineInputBorder(),
                  ),
                ),

                const SizedBox(height: 12),

                TextField(
                  controller:
                  _telefonoController,
                  keyboardType:
                  TextInputType.phone,
                  decoration:
                  const InputDecoration(
                    labelText: 'Teléfono',
                    border:
                    OutlineInputBorder(),
                  ),
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 5. MULTILÍNEA
          ElementoDemo(
            titulo: 'Campo multilínea',
            descripcion:
            'Permite escribir varias líneas de texto. '
                'Es útil para comentarios, observaciones '
                'o descripciones más extensas.',
            contenido: TextField(
              controller:
              _comentariosController,
              minLines: 3,
              maxLines: 5,
              decoration:
              const InputDecoration(
                labelText: 'Comentarios',
                border:
                OutlineInputBorder(),
              ),
            ),
          ),

          const SizedBox(height: 16),

          // 6. DESPLEGABLE
          ElementoDemo(
            titulo:
            'Campo con sugerencias (desplegable)',
            descripcion:
            'Muestra una lista de opciones predefinidas '
                'para que el usuario seleccione una '
                'sin tener que escribirla manualmente.',
            contenido:
            DropdownButtonFormField<String>(
              initialValue:
              _opcionSeleccionada,
              decoration:
              const InputDecoration(
                labelText:
                'Selecciona una opción',
                border:
                OutlineInputBorder(),
              ),
              items: _opciones
                  .map(
                    (opcion) =>
                    DropdownMenuItem<String>(
                      value: opcion,
                      child: Text(opcion),
                    ),
              )
                  .toList(),
              onChanged: (valor) {
                if (valor == null) {
                  return;
                }

                setState(() {
                  _opcionSeleccionada =
                      valor;
                });
              },
            ),
          ),

          const SizedBox(height: 16),

          // 7. BÚSQUEDA
          ElementoDemo(
            titulo: 'Barra de búsqueda',
            descripcion:
            'Permite filtrar una colección conforme '
                'el usuario escribe. Los resultados '
                'se actualizan automáticamente.',
            contenido: Column(
              crossAxisAlignment:
              CrossAxisAlignment.start,
              children: [

                TextField(
                  controller:
                  _busquedaController,
                  onChanged: (_) {
                    setState(() {});
                  },
                  decoration:
                  const InputDecoration(
                    hintText:
                    'Buscar sección...',
                    prefixIcon:
                    Icon(Icons.search),
                    border:
                    OutlineInputBorder(),
                  ),
                ),

                const SizedBox(height: 8),

                if (resultadosBusqueda.isEmpty)
                  const Text(
                    'Sin resultados',
                  )
                else
                  ...resultadosBusqueda.map(
                        (seccion) => Padding(
                      padding:
                      const EdgeInsets
                          .symmetric(
                        vertical: 2,
                      ),
                      child: Text(
                        '• $seccion',
                      ),
                    ),
                  ),
              ],
            ),
          ),

          const SizedBox(height: 24),
        ],
      ),
    );
  }
}