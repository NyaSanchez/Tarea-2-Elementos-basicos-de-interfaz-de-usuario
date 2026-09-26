import 'package:flutter/material.dart';
import '../widgets/elemento_demo.dart';

class BotonesAccionesScreen extends StatefulWidget {
  const BotonesAccionesScreen({super.key});

  @override
  State<BotonesAccionesScreen> createState() =>
      _BotonesAccionesScreenState();
}

class _BotonesAccionesScreenState
    extends State<BotonesAccionesScreen> {

  String _respuesta =
      'Interactúa con algún botón para ver su respuesta.';

  Set<String> _segmentoSeleccionado = {'Uno'};

  bool _cargando = false;

  void _mostrarRespuesta(String mensaje) {
    setState(() {
      _respuesta = mensaje;
    });
  }

  Future<void> _iniciarCarga() async {
    if (_cargando) return;

    setState(() {
      _cargando = true;
      _respuesta = 'La operación está en proceso...';
    });

    await Future.delayed(
      const Duration(milliseconds: 1500),
    );

    if (!mounted) return;

    setState(() {
      _cargando = false;
      _respuesta = 'La operación terminó correctamente.';
    });
  }

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [

          // RESPUESTA VISIBLE
          Card(
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: Text(
                'Respuesta: $_respuesta',
                style: Theme.of(context)
                    .textTheme
                    .bodyLarge
                    ?.copyWith(
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
          ),

          const SizedBox(height: 16),

          // 1. BOTONES BÁSICOS
          ElementoDemo(
            titulo:
            'Botón relleno, con contorno y de texto',
            descripcion:
            'Estas variantes representan diferentes niveles de importancia. '
                'El botón relleno suele utilizarse para la acción principal, '
                'mientras que los otros representan acciones secundarias.',
            contenido: Column(
              crossAxisAlignment:
              CrossAxisAlignment.stretch,
              children: [

                FilledButton(
                  onPressed: () {
                    _mostrarRespuesta(
                      'Pulsaste el botón relleno.',
                    );
                  },
                  child: const Text(
                    'Botón relleno',
                  ),
                ),

                const SizedBox(height: 8),

                OutlinedButton(
                  onPressed: () {
                    _mostrarRespuesta(
                      'Pulsaste el botón con contorno.',
                    );
                  },
                  child: const Text(
                    'Botón con contorno',
                  ),
                ),

                const SizedBox(height: 8),

                TextButton(
                  onPressed: () {
                    _mostrarRespuesta(
                      'Pulsaste el botón de solo texto.',
                    );
                  },
                  child: const Text(
                    'Botón de solo texto',
                  ),
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 2. BOTONES CON ÍCONO
          ElementoDemo(
            titulo: 'Botones con ícono',
            descripcion:
            'Los íconos permiten representar acciones visualmente. '
                'Pueden utilizarse de forma independiente o acompañados '
                'de texto para explicar mejor la acción.',
            contenido: Row(
              children: [

                IconButton.filled(
                  onPressed: () {
                    _mostrarRespuesta(
                      'Pulsaste el botón de solo ícono.',
                    );
                  },
                  icon: const Icon(
                    Icons.send,
                  ),
                  tooltip: 'Enviar',
                ),

                const SizedBox(width: 16),

                Expanded(
                  child: FilledButton.icon(
                    onPressed: () {
                      _mostrarRespuesta(
                        'Pulsaste el botón con ícono y texto.',
                      );
                    },
                    icon: const Icon(
                      Icons.send,
                    ),
                    label: const Text(
                      'Enviar',
                    ),
                  ),
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 3. FAB NORMAL Y EXTENDIDO
          ElementoDemo(
            titulo:
            'Botón de acción flotante',
            descripcion:
            'El botón de acción flotante destaca una acción importante. '
                'Puede aparecer solamente con un ícono o extenderse para '
                'incluir también una etiqueta de texto.',
            contenido: Wrap(
              spacing: 20,
              runSpacing: 12,
              crossAxisAlignment:
              WrapCrossAlignment.center,
              children: [

                FloatingActionButton(
                  heroTag: 'fab_normal',
                  onPressed: () {
                    _mostrarRespuesta(
                      'Pulsaste el FAB normal.',
                    );
                  },
                  child: const Icon(
                    Icons.add,
                  ),
                ),

                FloatingActionButton.extended(
                  heroTag: 'fab_extendido',
                  onPressed: () {
                    _mostrarRespuesta(
                      'Pulsaste el FAB extendido.',
                    );
                  },
                  icon: const Icon(
                    Icons.add,
                  ),
                  label: const Text(
                    'Agregar',
                  ),
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 4. SELECTOR SEGMENTADO
          ElementoDemo(
            titulo:
            'Botón de alternancia o selector segmentado',
            descripcion:
            'El selector segmentado permite elegir una opción dentro '
                'de un pequeño conjunto de alternativas. En este ejemplo '
                'solo una opción puede permanecer seleccionada.',
            contenido: Column(
              crossAxisAlignment:
              CrossAxisAlignment.start,
              children: [

                SegmentedButton<String>(
                  segments: const [

                    ButtonSegment<String>(
                      value: 'Uno',
                      label: Text('Uno'),
                    ),

                    ButtonSegment<String>(
                      value: 'Dos',
                      label: Text('Dos'),
                    ),

                    ButtonSegment<String>(
                      value: 'Tres',
                      label: Text('Tres'),
                    ),
                  ],
                  selected:
                  _segmentoSeleccionado,
                  multiSelectionEnabled: false,
                  emptySelectionAllowed: false,
                  onSelectionChanged:
                      (Set<String> seleccion) {

                    setState(() {
                      _segmentoSeleccionado =
                          seleccion;

                      _respuesta =
                      'Seleccionaste la opción ${seleccion.first}.';
                    });
                  },
                ),

                const SizedBox(height: 8),

                Text(
                  'Seleccionado: '
                      '${_segmentoSeleccionado.first}',
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 5. BOTÓN DESHABILITADO
          ElementoDemo(
            titulo:
            'Botón deshabilitado',
            descripcion:
            'Un botón deshabilitado comunica que una acción existe, '
                'pero todavía no está disponible porque falta cumplir '
                'alguna condición.',
            contenido: Column(
              crossAxisAlignment:
              CrossAxisAlignment.stretch,
              children: [

                const FilledButton(
                  onPressed: null,
                  child: Text(
                    'Acción no disponible',
                  ),
                ),

                const SizedBox(height: 8),

                Text(
                  'Este botón no responde porque se encuentra deshabilitado.',
                  style: Theme.of(context)
                      .textTheme
                      .bodySmall,
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 6. BOTÓN EN ESTADO DE CARGA
          ElementoDemo(
            titulo:
            'Botón en estado de carga',
            descripcion:
            'Durante una operación, el botón puede deshabilitarse '
                'temporalmente y mostrar un indicador de progreso para '
                'informar que la tarea todavía está ejecutándose.',
            contenido: SizedBox(
              width: double.infinity,
              child: FilledButton(
                onPressed:
                _cargando
                    ? null
                    : _iniciarCarga,
                child: _cargando
                    ? const Row(
                  mainAxisAlignment:
                  MainAxisAlignment.center,
                  children: [

                    SizedBox(
                      width: 20,
                      height: 20,
                      child:
                      CircularProgressIndicator(
                        strokeWidth: 2,
                      ),
                    ),

                    SizedBox(width: 12),

                    Text(
                      'Cargando...',
                    ),
                  ],
                )
                    : const Text(
                  'Iniciar carga',
                ),
              ),
            ),
          ),

          const SizedBox(height: 24),
        ],
      ),
    );
  }
}