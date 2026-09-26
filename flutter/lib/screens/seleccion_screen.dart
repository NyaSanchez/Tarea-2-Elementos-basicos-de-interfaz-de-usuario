import 'package:flutter/material.dart';

import '../widgets/elemento_demo.dart';

class SeleccionScreen extends StatefulWidget {
  const SeleccionScreen({super.key});

  @override
  State<SeleccionScreen> createState() =>
      _SeleccionScreenState();
}

class _SeleccionScreenState
    extends State<SeleccionScreen> {

  bool _checkNormal = false;

  bool? _checkTriEstado = false;

  String _radioSeleccionado = 'Android';

  bool _switchActivo = false;

  double _valorSlider = 50;

  RangeValues _rango = const RangeValues(
    20,
    80,
  );

  String _paisSeleccionado = 'México';

  DateTime? _fechaSeleccionada;

  TimeOfDay? _horaSeleccionada;

  final Set<String> _chipsSeleccionados = {};

  final List<String> _paises = [
    'México',
    'Canadá',
    'España',
    'Japón',
  ];

  String get _textoTriEstado {
    if (_checkTriEstado == null) {
      return 'Estado: indeterminado';
    }

    if (_checkTriEstado == true) {
      return 'Estado: marcado';
    }

    return 'Estado: desmarcado';
  }

  String _formatearFecha(
      DateTime fecha,
      ) {

    final dia =
    fecha.day.toString().padLeft(
      2,
      '0',
    );

    final mes =
    fecha.month.toString().padLeft(
      2,
      '0',
    );

    return '$dia/$mes/${fecha.year}';
  }

  Future<void> _seleccionarFecha() async {

    final ahora = DateTime.now();

    final fecha =
    await showDatePicker(
      context: context,
      initialDate:
      _fechaSeleccionada ?? ahora,
      firstDate: DateTime(2020),
      lastDate: DateTime(2035),
      helpText:
      'Selecciona una fecha',
      cancelText:
      'Cancelar',
      confirmText:
      'Aceptar',
      locale:
      const Locale(
        'es',
        'MX',
      ),
    );

    if (fecha != null) {

      setState(() {
        _fechaSeleccionada =
            fecha;
      });
    }
  }

  Future<void> _seleccionarHora() async {

    final hora =
    await showTimePicker(
      context: context,
      initialTime:
      _horaSeleccionada ??
          TimeOfDay.now(),
      helpText:
      'Selecciona una hora',
      cancelText:
      'Cancelar',
      confirmText:
      'Aceptar',
      hourLabelText:
      'Hora',
      minuteLabelText:
      'Minuto',
    );

    if (hora != null) {

      setState(() {
        _horaSeleccionada =
            hora;
      });
    }
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

          // 1. CHECKBOX NORMAL
          ElementoDemo(
            titulo:
            'Casilla de verificación',
            descripcion:
            'Permite activar o desactivar una opción de forma independiente. '
                'Es útil cuando pueden elegirse varias opciones al mismo tiempo.',
            contenido:
            CheckboxListTile(
              contentPadding:
              EdgeInsets.zero,
              title: const Text(
                'Aceptar notificaciones',
              ),
              value:
              _checkNormal,
              onChanged: (valor) {

                setState(() {
                  _checkNormal =
                      valor ?? false;
                });
              },
            ),
          ),

          const SizedBox(height: 16),

          // 2. CHECKBOX TRIESTADO
          ElementoDemo(
            titulo:
            'Casilla con estado indeterminado',
            descripcion:
            'Una casilla puede representar tres estados: marcada, '
                'desmarcada e indeterminada. El estado indeterminado indica '
                'una selección parcial.',
            contenido: Column(
              crossAxisAlignment:
              CrossAxisAlignment.start,
              children: [

                CheckboxListTile(
                  contentPadding:
                  EdgeInsets.zero,
                  title: const Text(
                    'Selección parcial',
                  ),
                  tristate: true,
                  value:
                  _checkTriEstado,
                  onChanged:
                      (valor) {

                    setState(() {
                      _checkTriEstado =
                          valor;
                    });
                  },
                ),

                Text(
                  _textoTriEstado,
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 3. RADIO BUTTONS
          ElementoDemo(
            titulo:
            'Grupo de botones de opción',
            descripcion:
            'Los botones de opción permiten seleccionar solamente una '
                'alternativa de un conjunto de opciones mutuamente excluyentes.',
            contenido:
            RadioGroup<String>(
              groupValue:
              _radioSeleccionado,
              onChanged:
                  (valor) {

                if (valor == null) {
                  return;
                }

                setState(() {
                  _radioSeleccionado =
                      valor;
                });
              },
              child: Column(
                children: const [

                  RadioListTile<String>(
                    contentPadding:
                    EdgeInsets.zero,
                    value:
                    'Android',
                    title:
                    Text(
                      'Android',
                    ),
                  ),

                  RadioListTile<String>(
                    contentPadding:
                    EdgeInsets.zero,
                    value:
                    'Flutter',
                    title:
                    Text(
                      'Flutter',
                    ),
                  ),

                  RadioListTile<String>(
                    contentPadding:
                    EdgeInsets.zero,
                    value:
                    'Otro',
                    title:
                    Text(
                      'Otro',
                    ),
                  ),
                ],
              ),
            ),
          ),

          const SizedBox(height: 16),

          // 4. SWITCH
          ElementoDemo(
            titulo:
            'Interruptor (switch)',
            descripcion:
            'El interruptor representa una configuración que puede '
                'encontrarse activada o desactivada.',
            contenido: Column(
              crossAxisAlignment:
              CrossAxisAlignment.start,
              children: [

                SwitchListTile(
                  contentPadding:
                  EdgeInsets.zero,
                  title: const Text(
                    'Sincronización automática',
                  ),
                  value:
                  _switchActivo,
                  onChanged:
                      (valor) {

                    setState(() {
                      _switchActivo =
                          valor;
                    });
                  },
                ),

                Text(
                  _switchActivo
                      ? 'Estado: activado'
                      : 'Estado: desactivado',
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 5. SLIDER
          ElementoDemo(
            titulo:
            'Deslizador de valor único',
            descripcion:
            'Permite seleccionar un valor dentro de un intervalo '
                'arrastrando un control a lo largo de una barra.',
            contenido: Column(
              children: [

                Text(
                  'Valor: '
                      '${_valorSlider.round()}',
                ),

                Slider(
                  value:
                  _valorSlider,
                  min:
                  0,
                  max:
                  100,
                  divisions:
                  100,
                  label:
                  _valorSlider
                      .round()
                      .toString(),
                  onChanged:
                      (valor) {

                    setState(() {
                      _valorSlider =
                          valor;
                    });
                  },
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 6. RANGE SLIDER
          ElementoDemo(
            titulo:
            'Deslizador de rango',
            descripcion:
            'Permite elegir dos valores dentro del mismo intervalo, '
                'por ejemplo un valor mínimo y uno máximo.',
            contenido: Column(
              children: [

                Text(
                  'Rango: '
                      '${_rango.start.round()} - '
                      '${_rango.end.round()}',
                ),

                RangeSlider(
                  values:
                  _rango,
                  min:
                  0,
                  max:
                  100,
                  divisions:
                  100,
                  labels:
                  RangeLabels(
                    _rango.start
                        .round()
                        .toString(),
                    _rango.end
                        .round()
                        .toString(),
                  ),
                  onChanged:
                      (valores) {

                    setState(() {
                      _rango =
                          valores;
                    });
                  },
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 7. DESPLEGABLE
          ElementoDemo(
            titulo:
            'Lista desplegable de selección',
            descripcion:
            'Permite elegir un elemento de una lista de opciones '
                'utilizando poco espacio en la interfaz.',
            contenido:
            DropdownButtonFormField<String>(
              initialValue:
              _paisSeleccionado,
              decoration:
              const InputDecoration(
                labelText:
                'Selecciona un país',
                border:
                OutlineInputBorder(),
              ),
              items:
              _paises.map(
                    (pais) {

                  return DropdownMenuItem<String>(
                    value:
                    pais,
                    child:
                    Text(pais),
                  );
                },
              ).toList(),
              onChanged:
                  (valor) {

                if (valor == null) {
                  return;
                }

                setState(() {
                  _paisSeleccionado =
                      valor;
                });
              },
            ),
          ),

          const SizedBox(height: 16),

          // 8. FECHA
          ElementoDemo(
            titulo:
            'Selector de fecha',
            descripcion:
            'Abre un calendario especializado para seleccionar una '
                'fecha válida sin necesidad de escribirla manualmente.',
            contenido: Column(
              crossAxisAlignment:
              CrossAxisAlignment.stretch,
              children: [

                FilledButton.icon(
                  onPressed:
                  _seleccionarFecha,
                  icon:
                  const Icon(
                    Icons.calendar_month,
                  ),
                  label:
                  const Text(
                    'Seleccionar fecha',
                  ),
                ),

                const SizedBox(height: 8),

                Text(
                  _fechaSeleccionada ==
                      null
                      ? 'Fecha: sin seleccionar'
                      : 'Fecha: '
                      '${_formatearFecha(_fechaSeleccionada!)}',
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 9. HORA
          ElementoDemo(
            titulo:
            'Selector de hora',
            descripcion:
            'Muestra un selector especializado para elegir una hora '
                'y sus minutos de forma clara y controlada.',
            contenido: Column(
              crossAxisAlignment:
              CrossAxisAlignment.stretch,
              children: [

                OutlinedButton.icon(
                  onPressed:
                  _seleccionarHora,
                  icon:
                  const Icon(
                    Icons.access_time,
                  ),
                  label:
                  const Text(
                    'Seleccionar hora',
                  ),
                ),

                const SizedBox(height: 8),

                Text(
                  _horaSeleccionada ==
                      null
                      ? 'Hora: sin seleccionar'
                      : 'Hora: '
                      '${_horaSeleccionada!.format(context)}',
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 10. CHIPS
          ElementoDemo(
            titulo:
            'Chips de filtro seleccionables',
            descripcion:
            'Los chips representan filtros o categorías compactas. '
                'En este ejemplo pueden seleccionarse varias opciones '
                'al mismo tiempo.',
            contenido: Column(
              crossAxisAlignment:
              CrossAxisAlignment.start,
              children: [

                Wrap(
                  spacing:
                  8,
                  runSpacing:
                  8,
                  children: [

                    FilterChip(
                      label:
                      const Text(
                        'Android',
                      ),
                      selected:
                      _chipsSeleccionados
                          .contains(
                        'Android',
                      ),
                      onSelected:
                          (seleccionado) {

                        _cambiarChip(
                          'Android',
                          seleccionado,
                        );
                      },
                    ),

                    FilterChip(
                      label:
                      const Text(
                        'Kotlin',
                      ),
                      selected:
                      _chipsSeleccionados
                          .contains(
                        'Kotlin',
                      ),
                      onSelected:
                          (seleccionado) {

                        _cambiarChip(
                          'Kotlin',
                          seleccionado,
                        );
                      },
                    ),

                    FilterChip(
                      label:
                      const Text(
                        'Flutter',
                      ),
                      selected:
                      _chipsSeleccionados
                          .contains(
                        'Flutter',
                      ),
                      onSelected:
                          (seleccionado) {

                        _cambiarChip(
                          'Flutter',
                          seleccionado,
                        );
                      },
                    ),
                  ],
                ),

                const SizedBox(height: 10),

                Text(
                  _chipsSeleccionados.isEmpty
                      ? 'Filtros: ninguno'
                      : 'Filtros: '
                      '${_chipsSeleccionados.join(', ')}',
                ),
              ],
            ),
          ),

          const SizedBox(height: 24),
        ],
      ),
    );
  }

  void _cambiarChip(
      String nombre,
      bool seleccionado,
      ) {

    setState(() {

      if (seleccionado) {

        _chipsSeleccionados.add(
          nombre,
        );

      } else {

        _chipsSeleccionados.remove(
          nombre,
        );
      }
    });
  }
}