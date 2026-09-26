import 'package:flutter/material.dart';
import '../widgets/elemento_demo.dart';

class EntradaTextoScreen extends StatefulWidget {
  const EntradaTextoScreen({super.key});

  @override
  State<EntradaTextoScreen> createState() => _EntradaTextoScreenState();
}

class _EntradaTextoScreenState extends State<EntradaTextoScreen> {
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
  final List<String> _opciones = ['Opción A', 'Opción B', 'Opción C', 'Opción D'];

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
        _usuarioController.text.isNotEmpty && _usuarioController.text.length < 4;
    final resultadosBusqueda = _todasLasSecciones
        .where((s) => s.toLowerCase().contains(_busquedaController.text.toLowerCase()))
        .toList();

    return SingleChildScrollView(
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          // 1. Campo simple
          ElementoDemo(
            titulo: 'Campo de texto simple',
            descripcion: 'Captura texto libre. La etiqueta indica qué se espera.',
            contenido: TextField(
              controller: _nombreController,
              decoration: const InputDecoration(
                labelText: 'Nombre',
                border: OutlineInputBorder(),
              ),
            ),
          ),
          const SizedBox(height: 16),

          // 2. Validación
          ElementoDemo(
            titulo: 'Campo con validación',
            descripcion:
            'Valida el contenido mientras se escribe y muestra un mensaje de error visible si no cumple la regla.',
            contenido: TextField(
              controller: _usuarioController,
              onChanged: (_) => setState(() {}),
              decoration: InputDecoration(
                labelText: 'Usuario',
                border: const OutlineInputBorder(),
                errorText: usuarioInvalido ? 'Debe tener al menos 4 caracteres' : null,
              ),
            ),
          ),
          const SizedBox(height: 16),

          // 3. Contraseña
          ElementoDemo(
            titulo: 'Campo de contraseña',
            descripcion:
            'Oculta el texto escrito por defecto. El ícono permite mostrarlo u ocultarlo.',
            contenido: TextField(
              controller: _passwordController,
              obscureText: !_passwordVisible,
              decoration: InputDecoration(
                labelText: 'Contraseña',
                border: const OutlineInputBorder(),
                suffixIcon: IconButton(
                  icon: Icon(_passwordVisible ? Icons.visibility_off : Icons.visibility),
                  onPressed: () => setState(() => _passwordVisible = !_passwordVisible),
                ),
              ),
            ),
          ),
          const SizedBox(height: 16),

          // 4. Tipos de teclado
          ElementoDemo(
            titulo: 'Campos con distintos tipos de teclado',
            descripcion:
            'El teclado que aparece cambia según el tipo de dato esperado: numérico, correo o teléfono.',
            contenido: Column(
              children: [
                TextField(
                  controller: _edadController,
                  keyboardType: TextInputType.number,
                  decoration:
                  const InputDecoration(labelText: 'Edad (numérico)', border: OutlineInputBorder()),
                ),
                const SizedBox(height: 12),
                TextField(
                  controller: _correoController,
                  keyboardType: TextInputType.emailAddress,
                  decoration:
                  const InputDecoration(labelText: 'Correo electrónico', border: OutlineInputBorder()),
                ),
                const SizedBox(height: 12),
                TextField(
                  controller: _telefonoController,
                  keyboardType: TextInputType.phone,
                  decoration: const InputDecoration(labelText: 'Teléfono', border: OutlineInputBorder()),
                ),
              ],
            ),
          ),
          const SizedBox(height: 16),

          // 5. Multilínea
          ElementoDemo(
            titulo: 'Campo multilínea',
            descripcion:
            'Permite escribir varias líneas de texto, útil para comentarios o descripciones largas.',
            contenido: TextField(
              controller: _comentariosController,
              minLines: 3,
              maxLines: 5,
              decoration: const InputDecoration(labelText: 'Comentarios', border: OutlineInputBorder()),
            ),
          ),
          const SizedBox(height: 16),

          // 6. Desplegable
          ElementoDemo(
            titulo: 'Campo con sugerencias (desplegable)',
            descripcion:
            'Muestra una lista de opciones predefinidas para que el usuario elija una en vez de escribirla.',
            contenido: DropdownButtonFormField<String>(
              initialValue: _opcionSeleccionada,
              decoration:
              const InputDecoration(labelText: 'Selecciona una opción', border: OutlineInputBorder()),
              items: _opciones
                  .map((opcion) => DropdownMenuItem(value: opcion, child: Text(opcion)))
                  .toList(),
              onChanged: (valor) => setState(() => _opcionSeleccionada = valor!),
            ),
          ),
          const SizedBox(height: 16),

          // 7. Búsqueda
          ElementoDemo(
            titulo: 'Barra de búsqueda',
            descripcion: 'Filtra una lista en tiempo real conforme el usuario escribe.',
            contenido: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                TextField(
                  controller: _busquedaController,
                  onChanged: (_) => setState(() {}),
                  decoration: const InputDecoration(
                    hintText: 'Buscar sección...',
                    prefixIcon: Icon(Icons.search),
                    border: OutlineInputBorder(),
                  ),
                ),
                const SizedBox(height: 8),
                if (resultadosBusqueda.isEmpty)
                  const Text('Sin resultados')
                else
                  ...resultadosBusqueda.map(
                        (s) => Padding(
                      padding: const EdgeInsets.symmetric(vertical: 2),
                      child: Text('• $s'),
                    ),
                  ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}