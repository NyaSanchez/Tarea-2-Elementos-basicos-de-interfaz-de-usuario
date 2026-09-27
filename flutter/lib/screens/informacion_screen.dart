import 'package:flutter/material.dart';

import '../widgets/elemento_demo.dart';

class InformacionScreen extends StatefulWidget {
  const InformacionScreen({super.key});

  @override
  State<InformacionScreen> createState() =>
      _InformacionScreenState();
}

class _InformacionScreenState
    extends State<InformacionScreen> {

  double _progreso = 0.40;

  bool _mostrarIndeterminado = false;

  int _notificaciones = 3;

  BoxFit _modoEscala = BoxFit.cover;

  String _nombreEscala = 'Recortar';

  double _tamanoTexto = 26;

  FontWeight _pesoTexto = FontWeight.bold;

  String _nombreEstilo = 'Título';

  String _resultadoDialogo =
      'Resultado: sin respuesta';

  void _cambiarEstilo({
    required String nombre,
    required double tamano,
    required FontWeight peso,
  }) {

    setState(() {
      _nombreEstilo = nombre;
      _tamanoTexto = tamano;
      _pesoTexto = peso;
    });
  }

  void _cambiarEscala(
      BoxFit modo,
      String nombre,
      ) {

    setState(() {
      _modoEscala = modo;
      _nombreEscala = nombre;
    });
  }

  void _mostrarToast() {

    final overlay =
    Overlay.of(context);

    late OverlayEntry entrada;

    entrada = OverlayEntry(
      builder: (context) {

        return Positioned(
          left: 40,
          right: 40,
          bottom: 80,
          child: SafeArea(
            child: Material(
              color: Colors.transparent,
              child: Center(
                child: Container(
                  padding:
                  const EdgeInsets.symmetric(
                    horizontal: 18,
                    vertical: 12,
                  ),
                  decoration: BoxDecoration(
                    color:
                    Colors.black87,
                    borderRadius:
                    BorderRadius.circular(24),
                  ),
                  child: const Text(
                    'Este es un mensaje breve',
                    style: TextStyle(
                      color: Colors.white,
                    ),
                  ),
                ),
              ),
            ),
          ),
        );
      },
    );

    overlay.insert(entrada);

    Future.delayed(
      const Duration(
        milliseconds: 1800,
      ),
          () {

        entrada.remove();
      },
    );
  }

  void _mostrarSnackbar() {

    ScaffoldMessenger.of(context)
        .hideCurrentSnackBar();

    ScaffoldMessenger.of(context)
        .showSnackBar(
      SnackBar(
        content:
        const Text(
          'Elemento eliminado',
        ),
        action:
        SnackBarAction(
          label:
          'Deshacer',
          onPressed: () {

            ScaffoldMessenger.of(context)
                .showSnackBar(
              const SnackBar(
                content:
                Text(
                  'Acción deshecha',
                ),
              ),
            );
          },
        ),
      ),
    );
  }

  Future<void> _mostrarDialogo() async {

    final resultado =
    await showDialog<bool>(
      context: context,
      builder: (context) {

        return AlertDialog(
          title:
          const Text(
            '¿Confirmar acción?',
          ),
          content:
          const Text(
            'Esta acción no se puede deshacer. '
                '¿Deseas continuar?',
          ),
          actions: [

            TextButton(
              onPressed: () {

                Navigator.pop(
                  context,
                  false,
                );
              },
              child:
              const Text(
                'Cancelar',
              ),
            ),

            FilledButton(
              onPressed: () {

                Navigator.pop(
                  context,
                  true,
                );
              },
              child:
              const Text(
                'Confirmar',
              ),
            ),
          ],
        );
      },
    );

    if (resultado == null) {
      return;
    }

    setState(() {

      _resultadoDialogo =
      resultado
          ? 'Resultado: confirmaste la acción'
          : 'Resultado: cancelaste la acción';
    });
  }

  void _mostrarBottomSheet() {

    showModalBottomSheet<void>(
      context: context,
      showDragHandle: true,
      builder: (context) {

        return SafeArea(
          child: Padding(
            padding:
            const EdgeInsets.fromLTRB(
              24,
              8,
              24,
              32,
            ),
            child: Column(
              mainAxisSize:
              MainAxisSize.min,
              crossAxisAlignment:
              CrossAxisAlignment.start,
              children: [

                Text(
                  'Opciones',
                  style:
                  Theme.of(context)
                      .textTheme
                      .titleLarge,
                ),

                const SizedBox(
                  height: 12,
                ),

                const Text(
                  'Este panel aparece desde abajo '
                      'y permite mostrar opciones adicionales '
                      'sin abandonar la pantalla actual.',
                ),

                const SizedBox(
                  height: 16,
                ),

                ListTile(
                  contentPadding:
                  EdgeInsets.zero,
                  leading:
                  const Icon(
                    Icons.share,
                  ),
                  title:
                  const Text(
                    'Compartir',
                  ),
                  onTap: () {

                    Navigator.pop(context);
                  },
                ),

                ListTile(
                  contentPadding:
                  EdgeInsets.zero,
                  leading:
                  const Icon(
                    Icons.favorite_border,
                  ),
                  title:
                  const Text(
                    'Agregar a favoritos',
                  ),
                  onTap: () {

                    Navigator.pop(context);
                  },
                ),
              ],
            ),
          ),
        );
      },
    );
  }

  @override
  Widget build(
      BuildContext context,
      ) {

    return SingleChildScrollView(
      padding:
      const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment:
        CrossAxisAlignment.stretch,
        children: [

          // 1. TIPOGRAFÍA
          ElementoDemo(
            titulo:
            'Textos con distintos estilos, tamaños y énfasis',
            descripcion:
            'La tipografía permite crear jerarquía visual. '
                'Los títulos, subtítulos, cuerpos y etiquetas pueden '
                'utilizar distintos tamaños y pesos.',
            contenido: Column(
              crossAxisAlignment:
              CrossAxisAlignment.start,
              children: [

                Wrap(
                  spacing: 8,
                  runSpacing: 8,
                  children: [

                    OutlinedButton(
                      onPressed: () {
                        _cambiarEstilo(
                          nombre: 'Título',
                          tamano: 26,
                          peso: FontWeight.bold,
                        );
                      },
                      child:
                      const Text(
                        'Título',
                      ),
                    ),

                    OutlinedButton(
                      onPressed: () {
                        _cambiarEstilo(
                          nombre: 'Subtítulo',
                          tamano: 20,
                          peso: FontWeight.w600,
                        );
                      },
                      child:
                      const Text(
                        'Subtítulo',
                      ),
                    ),

                    OutlinedButton(
                      onPressed: () {
                        _cambiarEstilo(
                          nombre: 'Cuerpo',
                          tamano: 16,
                          peso: FontWeight.normal,
                        );
                      },
                      child:
                      const Text(
                        'Cuerpo',
                      ),
                    ),

                    OutlinedButton(
                      onPressed: () {
                        _cambiarEstilo(
                          nombre: 'Etiqueta',
                          tamano: 12,
                          peso: FontWeight.normal,
                        );
                      },
                      child:
                      const Text(
                        'Etiqueta',
                      ),
                    ),
                  ],
                ),

                const SizedBox(
                  height: 16,
                ),

                Text(
                  'Texto de ejemplo: $_nombreEstilo',
                  style: TextStyle(
                    fontSize:
                    _tamanoTexto,
                    fontWeight:
                    _pesoTexto,
                  ),
                ),

                const Divider(
                  height: 28,
                ),

                const Text(
                  'Texto en negrita',
                  style: TextStyle(
                    fontWeight:
                    FontWeight.bold,
                  ),
                ),

                const Text(
                  'Texto en cursiva',
                  style: TextStyle(
                    fontStyle:
                    FontStyle.italic,
                  ),
                ),

                const Text(
                  'Texto subrayado',
                  style: TextStyle(
                    decoration:
                    TextDecoration.underline,
                  ),
                ),
              ],
            ),
          ),

          const SizedBox(
            height: 16,
          ),

          // 2. IMÁGENES
          ElementoDemo(
            titulo:
            'Imagen local e imagen desde URL',
            descripcion:
            'La primera imagen se almacena dentro de la aplicación '
                'y la segunda se obtiene desde Internet. '
                'El modo de escalado determina cómo ocupan el espacio disponible.',
            contenido: Column(
              children: [

                Row(
                  children: [

                    Expanded(
                      child: Column(
                        children: [

                          const Text(
                            'Local',
                          ),

                          const SizedBox(
                            height: 6,
                          ),

                          SizedBox(
                            height: 140,
                            width:
                            double.infinity,
                            child: Image.asset(
                              'assets/images/imagen_local_flutter.jpg',
                              fit:
                              _modoEscala,
                            ),
                          ),
                        ],
                      ),
                    ),

                    const SizedBox(
                      width: 12,
                    ),

                    Expanded(
                      child: Column(
                        children: [

                          const Text(
                            'Desde URL',
                          ),

                          const SizedBox(
                            height: 6,
                          ),

                          SizedBox(
                            height: 140,
                            width:
                            double.infinity,
                            child: Image.network(
                              'https://picsum.photos/400/300',
                              fit:
                              _modoEscala,
                              loadingBuilder:
                                  (
                                  context,
                                  child,
                                  progreso,
                                  ) {

                                if (progreso == null) {
                                  return child;
                                }

                                return const Center(
                                  child:
                                  CircularProgressIndicator(),
                                );
                              },
                              errorBuilder:
                                  (
                                  context,
                                  error,
                                  stackTrace,
                                  ) {

                                return const Center(
                                  child: Column(
                                    mainAxisAlignment:
                                    MainAxisAlignment.center,
                                    children: [
                                      Icon(
                                        Icons.broken_image,
                                        size: 42,
                                      ),
                                      Text(
                                        'No se pudo cargar',
                                      ),
                                    ],
                                  ),
                                );
                              },
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),

                const SizedBox(
                  height: 12,
                ),

                Text(
                  'Modo de escalado: $_nombreEscala',
                ),

                const SizedBox(
                  height: 8,
                ),

                Wrap(
                  spacing: 8,
                  runSpacing: 8,
                  children: [

                    OutlinedButton(
                      onPressed: () {
                        _cambiarEscala(
                          BoxFit.cover,
                          'Recortar',
                        );
                      },
                      child:
                      const Text(
                        'Recortar',
                      ),
                    ),

                    OutlinedButton(
                      onPressed: () {
                        _cambiarEscala(
                          BoxFit.contain,
                          'Ajustar',
                        );
                      },
                      child:
                      const Text(
                        'Ajustar',
                      ),
                    ),

                    OutlinedButton(
                      onPressed: () {
                        _cambiarEscala(
                          BoxFit.fill,
                          'Rellenar',
                        );
                      },
                      child:
                      const Text(
                        'Rellenar',
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),

          const SizedBox(
            height: 16,
          ),

          // 3. PROGRESO
          ElementoDemo(
            titulo:
            'Indicadores de progreso',
            descripcion:
            'El progreso determinado muestra un avance conocido. '
                'El indeterminado indica que existe una operación activa '
                'pero no se conoce exactamente cuánto falta.',
            contenido: Column(
              children: [

                Text(
                  'Progreso: '
                      '${(_progreso * 100).round()}%',
                ),

                Slider(
                  value:
                  _progreso,
                  min:
                  0,
                  max:
                  1,
                  divisions:
                  100,
                  onChanged:
                      (valor) {

                    setState(() {
                      _progreso =
                          valor;
                    });
                  },
                ),

                LinearProgressIndicator(
                  value:
                  _progreso,
                ),

                const SizedBox(
                  height: 18,
                ),

                SizedBox(
                  width: 56,
                  height: 56,
                  child:
                  CircularProgressIndicator(
                    value:
                    _progreso,
                  ),
                ),

                const SizedBox(
                  height: 16,
                ),

                FilledButton(
                  onPressed: () {

                    setState(() {
                      _mostrarIndeterminado =
                      !_mostrarIndeterminado;
                    });
                  },
                  child: Text(
                    _mostrarIndeterminado
                        ? 'Ocultar indeterminado'
                        : 'Mostrar indeterminado',
                  ),
                ),

                if (_mostrarIndeterminado) ...[

                  const SizedBox(
                    height: 16,
                  ),

                  const LinearProgressIndicator(),

                  const SizedBox(
                    height: 16,
                  ),

                  const CircularProgressIndicator(),
                ],
              ],
            ),
          ),

          const SizedBox(
            height: 16,
          ),

          // 4. TOAST Y SNACKBAR
          ElementoDemo(
            titulo:
            'Mensaje emergente breve y snackbar',
            descripcion:
            'El mensaje breve desaparece automáticamente después '
                'de unos segundos. El snackbar permanece temporalmente '
                'y puede incluir una acción como deshacer.',
            contenido: Wrap(
              spacing: 10,
              runSpacing: 10,
              children: [

                FilledButton(
                  onPressed:
                  _mostrarToast,
                  child:
                  const Text(
                    'Mostrar toast',
                  ),
                ),

                OutlinedButton(
                  onPressed:
                  _mostrarSnackbar,
                  child:
                  const Text(
                    'Mostrar snackbar',
                  ),
                ),
              ],
            ),
          ),

          const SizedBox(
            height: 16,
          ),

          // 5. DIÁLOGO
          ElementoDemo(
            titulo:
            'Diálogo de confirmación',
            descripcion:
            'Solicita que el usuario confirme o cancele una acción '
                'antes de continuar con una operación importante.',
            contenido: Column(
              crossAxisAlignment:
              CrossAxisAlignment.stretch,
              children: [

                FilledButton(
                  onPressed:
                  _mostrarDialogo,
                  child:
                  const Text(
                    'Eliminar cuenta',
                  ),
                ),

                const SizedBox(
                  height: 8,
                ),

                Text(
                  _resultadoDialogo,
                ),
              ],
            ),
          ),

          const SizedBox(
            height: 16,
          ),

          // 6. BOTTOM SHEET
          ElementoDemo(
            titulo:
            'Hoja inferior (bottom sheet)',
            descripcion:
            'La hoja inferior aparece desde la parte baja de la pantalla '
                'para mostrar opciones adicionales sin abandonar '
                'el contenido actual.',
            contenido:
            FilledButton(
              onPressed:
              _mostrarBottomSheet,
              child:
              const Text(
                'Abrir opciones',
              ),
            ),
          ),

          const SizedBox(
            height: 16,
          ),

          // 7. TARJETA / DIVIDER / BADGE
          ElementoDemo(
            titulo:
            'Tarjeta, separador y distintivo numérico',
            descripcion:
            'La tarjeta agrupa contenido relacionado, el separador '
                'divide visualmente información y el badge resalta '
                'una cantidad, como notificaciones pendientes.',
            contenido: Card(
              child: Padding(
                padding:
                const EdgeInsets.all(16),
                child: Column(
                  children: [

                    Row(
                      children: [

                        Expanded(
                          child: Text(
                            'Bandeja de entrada',
                            style:
                            Theme.of(context)
                                .textTheme
                                .titleMedium,
                          ),
                        ),

                        Badge(
                          isLabelVisible:
                          _notificaciones > 0,
                          label:
                          Text(
                            '$_notificaciones',
                          ),
                          child:
                          const Icon(
                            Icons.notifications,
                            size: 30,
                          ),
                        ),
                      ],
                    ),

                    const Divider(
                      height: 28,
                    ),

                    Row(
                      children: [

                        Expanded(
                          child: FilledButton(
                            onPressed: () {

                              setState(() {
                                _notificaciones++;
                              });
                            },
                            child:
                            const Text(
                              'Agregar',
                            ),
                          ),
                        ),

                        const SizedBox(
                          width: 8,
                        ),

                        Expanded(
                          child: OutlinedButton(
                            onPressed: () {

                              if (_notificaciones == 0) {
                                return;
                              }

                              setState(() {
                                _notificaciones--;
                              });
                            },
                            child:
                            const Text(
                              'Marcar leída',
                            ),
                          ),
                        ),
                      ],
                    ),
                  ],
                ),
              ),
            ),
          ),

          const SizedBox(
            height: 24,
          ),
        ],
      ),
    );
  }
}