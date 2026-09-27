import 'package:flutter/material.dart';

import '../widgets/elemento_demo.dart';

class ContenedoresScreen extends StatefulWidget {
  const ContenedoresScreen({super.key});

  @override
  State<ContenedoresScreen> createState() =>
      _ContenedoresScreenState();
}

class _ContenedoresScreenState
    extends State<ContenedoresScreen> {

  String _respuesta =
      'Interactúa con los ejemplos para observar su comportamiento.';

  bool _capaActiva = false;

  int _indiceNavegacion = 0;

  final List<String> _nombresNavegacion = [
    'Inicio',
    'Favoritos',
    'Perfil',
  ];

  void _mostrarRespuesta(String mensaje) {
    setState(() {
      _respuesta = mensaje;
    });
  }

  @override
  Widget build(BuildContext context) {

    final colores =
        Theme.of(context).colorScheme;

    return SingleChildScrollView(
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment:
        CrossAxisAlignment.stretch,
        children: [

          // RESPUESTA VISIBLE
          Card(
            child: Padding(
              padding:
              const EdgeInsets.all(16),
              child: Text(
                'Respuesta: $_respuesta',
                style:
                Theme.of(context)
                    .textTheme
                    .bodyLarge
                    ?.copyWith(
                  fontWeight:
                  FontWeight.bold,
                ),
              ),
            ),
          ),

          const SizedBox(height: 16),

          // 1. FILA
          ElementoDemo(
            titulo:
            'Distribución horizontal en fila',
            descripcion:
            'Row organiza sus elementos horizontalmente. '
                'Es útil para colocar controles relacionados uno al lado '
                'del otro dentro del espacio disponible.',
            contenido: Row(
              children: [

                Expanded(
                  child: FilledButton(
                    onPressed: () {
                      _mostrarRespuesta(
                        'Pulsaste el primer elemento de la fila.',
                      );
                    },
                    child:
                    const Text('Uno'),
                  ),
                ),

                const SizedBox(width: 8),

                Expanded(
                  child: FilledButton(
                    onPressed: () {
                      _mostrarRespuesta(
                        'Pulsaste el segundo elemento de la fila.',
                      );
                    },
                    child:
                    const Text('Dos'),
                  ),
                ),

                const SizedBox(width: 8),

                Expanded(
                  child: FilledButton(
                    onPressed: () {
                      _mostrarRespuesta(
                        'Pulsaste el tercer elemento de la fila.',
                      );
                    },
                    child:
                    const Text('Tres'),
                  ),
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 2. COLUMNA
          ElementoDemo(
            titulo:
            'Distribución vertical en columna',
            descripcion:
            'Column coloca sus elementos uno debajo de otro. '
                'Se utiliza cuando la información debe seguir '
                'una organización vertical.',
            contenido: Column(
              crossAxisAlignment:
              CrossAxisAlignment.stretch,
              children: [

                OutlinedButton.icon(
                  onPressed: () {
                    _mostrarRespuesta(
                      'Seleccionaste la opción superior.',
                    );
                  },
                  icon:
                  const Icon(Icons.looks_one),
                  label:
                  const Text('Opción superior'),
                ),

                const SizedBox(height: 8),

                OutlinedButton.icon(
                  onPressed: () {
                    _mostrarRespuesta(
                      'Seleccionaste la opción central.',
                    );
                  },
                  icon:
                  const Icon(Icons.looks_two),
                  label:
                  const Text('Opción central'),
                ),

                const SizedBox(height: 8),

                OutlinedButton.icon(
                  onPressed: () {
                    _mostrarRespuesta(
                      'Seleccionaste la opción inferior.',
                    );
                  },
                  icon:
                  const Icon(Icons.looks_3),
                  label:
                  const Text('Opción inferior'),
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 3. STACK
          ElementoDemo(
            titulo:
            'Elementos superpuestos',
            descripcion:
            'Stack permite colocar elementos en diferentes capas. '
                'Los widgets posteriores pueden funcionar como fondo '
                'mientras otros aparecen encima.',
            contenido: SizedBox(
              height: 190,
              child: Stack(
                children: [

                  Positioned.fill(
                    child: Container(
                      decoration: BoxDecoration(
                        color:
                        colores.secondaryContainer,
                        borderRadius:
                        BorderRadius.circular(16),
                      ),
                      alignment:
                      Alignment.center,
                      child: Icon(
                        Icons.layers,
                        size: 80,
                        color:
                        colores.onSecondaryContainer
                            .withAlpha(80),
                      ),
                    ),
                  ),

                  const Positioned(
                    top: 12,
                    left: 12,
                    child: Chip(
                      label:
                      Text('Capa posterior'),
                    ),
                  ),

                  if (_capaActiva)
                    Center(
                      child: Container(
                        padding:
                        const EdgeInsets.all(16),
                        decoration: BoxDecoration(
                          color:
                          colores.primaryContainer,
                          borderRadius:
                          BorderRadius.circular(16),
                        ),
                        child:
                        const Text(
                          'Capa intermedia activa',
                        ),
                      ),
                    ),

                  Positioned(
                    bottom: 12,
                    right: 12,
                    child:
                    FilledButton.icon(
                      onPressed: () {

                        setState(() {
                          _capaActiva =
                          !_capaActiva;

                          _respuesta =
                          _capaActiva
                              ? 'Se mostró una nueva capa superpuesta.'
                              : 'Se ocultó la capa superpuesta.';
                        });
                      },
                      icon:
                      const Icon(
                        Icons.layers_outlined,
                      ),
                      label: Text(
                        _capaActiva
                            ? 'Ocultar capa'
                            : 'Mostrar capa',
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),

          const SizedBox(height: 16),

          // 4. SCROLL VERTICAL
          ElementoDemo(
            titulo:
            'Contenedor con desplazamiento vertical',
            descripcion:
            'Un contenedor desplazable permite mostrar más contenido '
                'del que cabe en el espacio disponible. El usuario puede '
                'recorrerlo mediante un gesto vertical.',
            contenido: SizedBox(
              height: 240,
              child: Scrollbar(
                child: ListView.builder(
                  itemCount: 15,
                  itemBuilder:
                      (context, index) {

                    return ListTile(
                      leading:
                      CircleAvatar(
                        child:
                        Text(
                          '${index + 1}',
                        ),
                      ),
                      title:
                      Text(
                        'Contenido desplazable ${index + 1}',
                      ),
                      onTap: () {

                        _mostrarRespuesta(
                          'Seleccionaste el elemento ${index + 1} del contenedor.',
                        );
                      },
                    );
                  },
                ),
              ),
            ),
          ),

          const SizedBox(height: 16),

          // 5. BARRA SUPERIOR
          ElementoDemo(
            titulo:
            'Barra superior con título y acciones',
            descripcion:
            'AppBar identifica la pantalla actual y ofrece acciones '
                'importantes en una posición visible, como buscar '
                'o marcar contenido como favorito.',
            contenido: ClipRRect(
              borderRadius:
              BorderRadius.circular(12),
              child: SizedBox(
                height: kToolbarHeight,
                child: AppBar(
                  primary: false,
                  automaticallyImplyLeading:
                  false,
                  title:
                  const Text(
                    'Barra de ejemplo',
                  ),
                  actions: [

                    IconButton(
                      tooltip: 'Buscar',
                      onPressed: () {
                        _mostrarRespuesta(
                          'Pulsaste la acción Buscar.',
                        );
                      },
                      icon:
                      const Icon(
                        Icons.search,
                      ),
                    ),

                    IconButton(
                      tooltip: 'Favorito',
                      onPressed: () {
                        _mostrarRespuesta(
                          'Pulsaste la acción Favorito.',
                        );
                      },
                      icon:
                      const Icon(
                        Icons.favorite_border,
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),

          const SizedBox(height: 16),

          // 6. NAVEGACIÓN INFERIOR
          ElementoDemo(
            titulo:
            'Barra de navegación inferior',
            descripcion:
            'NavigationBar permite cambiar entre destinos principales '
                'de una aplicación. La opción seleccionada permanece '
                'visualmente destacada.',
            contenido: Column(
              children: [

                NavigationBar(
                  selectedIndex:
                  _indiceNavegacion,
                  onDestinationSelected:
                      (indice) {

                    setState(() {
                      _indiceNavegacion =
                          indice;

                      _respuesta =
                      'Destino seleccionado: '
                          '${_nombresNavegacion[indice]}.';
                    });
                  },
                  destinations:
                  const [

                    NavigationDestination(
                      icon:
                      Icon(
                        Icons.home_outlined,
                      ),
                      selectedIcon:
                      Icon(Icons.home),
                      label:
                      'Inicio',
                    ),

                    NavigationDestination(
                      icon:
                      Icon(
                        Icons.favorite_border,
                      ),
                      selectedIcon:
                      Icon(
                        Icons.favorite,
                      ),
                      label:
                      'Favoritos',
                    ),

                    NavigationDestination(
                      icon:
                      Icon(
                        Icons.person_outline,
                      ),
                      selectedIcon:
                      Icon(Icons.person),
                      label:
                      'Perfil',
                    ),
                  ],
                ),

                const SizedBox(height: 8),

                Text(
                  'Seleccionado: '
                      '${_nombresNavegacion[_indiceNavegacion]}',
                ),
              ],
            ),
          ),

          const SizedBox(height: 16),

          // 7. PESOS PROPORCIONALES
          ElementoDemo(
            titulo:
            'Distribución con pesos proporcionales',
            descripcion:
            'Expanded y su propiedad flex permiten repartir el espacio '
                'disponible de forma proporcional. En este ejemplo '
                'las áreas utilizan una relación de 1 : 2 : 1.',
            contenido: Column(
              children: [

                SizedBox(
                  height: 90,
                  child: Row(
                    children: [

                      Expanded(
                        flex: 1,
                        child: InkWell(
                          onTap: () {
                            _mostrarRespuesta(
                              'Seleccionaste el bloque con peso 1.',
                            );
                          },
                          child: Container(
                            alignment:
                            Alignment.center,
                            decoration:
                            BoxDecoration(
                              color:
                              colores.primaryContainer,
                              borderRadius:
                              BorderRadius.circular(12),
                            ),
                            child:
                            const Text(
                              '1',
                            ),
                          ),
                        ),
                      ),

                      const SizedBox(width: 8),

                      Expanded(
                        flex: 2,
                        child: InkWell(
                          onTap: () {
                            _mostrarRespuesta(
                              'Seleccionaste el bloque con peso 2.',
                            );
                          },
                          child: Container(
                            alignment:
                            Alignment.center,
                            decoration:
                            BoxDecoration(
                              color:
                              colores.secondaryContainer,
                              borderRadius:
                              BorderRadius.circular(12),
                            ),
                            child:
                            const Text(
                              '2',
                              style:
                              TextStyle(
                                fontWeight:
                                FontWeight.bold,
                              ),
                            ),
                          ),
                        ),
                      ),

                      const SizedBox(width: 8),

                      Expanded(
                        flex: 1,
                        child: InkWell(
                          onTap: () {
                            _mostrarRespuesta(
                              'Seleccionaste el segundo bloque con peso 1.',
                            );
                          },
                          child: Container(
                            alignment:
                            Alignment.center,
                            decoration:
                            BoxDecoration(
                              color:
                              colores.tertiaryContainer,
                              borderRadius:
                              BorderRadius.circular(12),
                            ),
                            child:
                            const Text(
                              '1',
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),

                const SizedBox(height: 10),

                const Text(
                  'Proporción utilizada: 1 : 2 : 1',
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