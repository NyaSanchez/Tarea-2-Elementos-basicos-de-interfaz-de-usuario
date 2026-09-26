import 'package:flutter/material.dart';

import '../widgets/elemento_demo.dart';

class ListasColeccionesScreen extends StatefulWidget {
  const ListasColeccionesScreen({super.key});

  @override
  State<ListasColeccionesScreen> createState() =>
      _ListasColeccionesScreenState();
}

class _ListasColeccionesScreenState
    extends State<ListasColeccionesScreen> {

  final List<String> _tareas = [
    'Tarea 1',
    'Tarea 2',
    'Tarea 3',
    'Tarea 4',
    'Tarea 5',
  ];

  final List<String> _registros = List.generate(
    10,
        (index) => 'Registro ${index + 1}',
  );

  int _actualizaciones = 0;

  final List<_ElementoSeccion> _elementosSeccion = [
    const _ElementoSeccion(
      texto: 'Frutas',
      encabezado: true,
    ),
    const _ElementoSeccion(
      texto: 'Manzana',
    ),
    const _ElementoSeccion(
      texto: 'Plátano',
    ),
    const _ElementoSeccion(
      texto: 'Naranja',
    ),
    const _ElementoSeccion(
      texto: 'Uva',
    ),
    const _ElementoSeccion(
      texto: 'Verduras',
      encabezado: true,
    ),
    const _ElementoSeccion(
      texto: 'Zanahoria',
    ),
    const _ElementoSeccion(
      texto: 'Brócoli',
    ),
    const _ElementoSeccion(
      texto: 'Espinaca',
    ),
    const _ElementoSeccion(
      texto: 'Papa',
    ),
  ];

  final List<Map<String, String>> _productos = [
    {
      'nombre': 'Producto A',
      'descripcion':
      'Disponible. Envío estimado en dos días.',
    },
    {
      'nombre': 'Producto B',
      'descripcion':
      'Agotado temporalmente.',
    },
    {
      'nombre': 'Producto C',
      'descripcion':
      'Producto en oferta durante esta semana.',
    },
    {
      'nombre': 'Producto D',
      'descripcion':
      'Nuevo ingreso al catálogo.',
    },
  ];

  void _mostrarDetalle(
      Map<String, String> producto,
      ) {

    showDialog<void>(
      context: context,
      builder: (context) {

        return AlertDialog(
          title: Text(
            producto['nombre']!,
          ),
          content: Text(
            producto['descripcion']!,
          ),
          actions: [

            TextButton(
              onPressed: () {
                Navigator.pop(context);
              },
              child: const Text(
                'Cerrar',
              ),
            ),
          ],
        );
      },
    );
  }

  Future<void> _actualizarLista() async {

    await Future.delayed(
      const Duration(
        milliseconds: 900,
      ),
    );

    if (!mounted) {
      return;
    }

    setState(() {

      _actualizaciones++;

      _registros.insert(
        0,
        'Nuevo registro $_actualizaciones',
      );
    });
  }

  void _reiniciarTareas() {

    setState(() {

      _tareas
        ..clear()
        ..addAll([
          'Tarea 1',
          'Tarea 2',
          'Tarea 3',
          'Tarea 4',
          'Tarea 5',
        ]);
    });
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

          // 1. LISTA VERTICAL
          ElementoDemo(
            titulo:
            'Lista vertical',
            descripcion:
            'ListView.builder permite mostrar grandes colecciones de forma eficiente. '
                'En esta demostración se presentan veinte elementos desplazables.',
            contenido: SizedBox(
              height:
              240,
              child: ListView.builder(
                itemCount:
                20,
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
                      'Elemento ${index + 1}',
                    ),
                  );
                },
              ),
            ),
          ),

          const SizedBox(
            height: 16,
          ),

          // 2. CUADRÍCULA
          ElementoDemo(
            titulo:
            'Cuadrícula de elementos',
            descripcion:
            'GridView organiza elementos en filas y columnas. '
                'En este ejemplo se muestran doce elementos distribuidos en tres columnas.',
            contenido: SizedBox(
              height:
              260,
              child:
              GridView.builder(
                gridDelegate:
                const SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount:
                  3,
                  crossAxisSpacing:
                  8,
                  mainAxisSpacing:
                  8,
                ),
                itemCount:
                12,
                itemBuilder:
                    (context, index) {

                  return Card(
                    child:
                    Center(
                      child:
                      Text(
                        '${index + 1}',
                        style:
                        Theme.of(context)
                            .textTheme
                            .titleLarge,
                      ),
                    ),
                  );
                },
              ),
            ),
          ),

          const SizedBox(
            height: 16,
          ),

          // 3. LISTA CON ENCABEZADOS
          ElementoDemo(
            titulo:
            'Lista con encabezados de sección',
            descripcion:
            'Una lista puede presentar diferentes tipos de fila. '
                'Aquí los encabezados separan categorías y los demás elementos representan datos normales.',
            contenido: SizedBox(
              height:
              300,
              child:
              ListView.builder(
                itemCount:
                _elementosSeccion.length,
                itemBuilder:
                    (context, index) {

                  final elemento =
                  _elementosSeccion[index];

                  if (elemento.encabezado) {

                    return Container(
                      padding:
                      const EdgeInsets.symmetric(
                        horizontal:
                        16,
                        vertical:
                        10,
                      ),
                      color:
                      Theme.of(context)
                          .colorScheme
                          .secondaryContainer,
                      child:
                      Text(
                        elemento.texto,
                        style:
                        Theme.of(context)
                            .textTheme
                            .titleMedium
                            ?.copyWith(
                          fontWeight:
                          FontWeight.bold,
                        ),
                      ),
                    );
                  }

                  return ListTile(
                    leading:
                    const Icon(
                      Icons.circle,
                      size:
                      10,
                    ),
                    title:
                    Text(
                      elemento.texto,
                    ),
                  );
                },
              ),
            ),
          ),

          const SizedBox(
            height: 16,
          ),

          // 4. DETALLE
          ElementoDemo(
            titulo:
            'Selección de elemento con detalle',
            descripcion:
            'Al tocar un elemento se abre un diálogo que muestra información adicional del elemento seleccionado.',
            contenido: SizedBox(
              height:
              230,
              child:
              ListView.separated(
                itemCount:
                _productos.length,
                separatorBuilder:
                    (_, _) =>
                const Divider(
                  height:
                  1,
                ),
                itemBuilder:
                    (context, index) {

                  final producto =
                  _productos[index];

                  return ListTile(
                    leading:
                    const Icon(
                      Icons.inventory_2_outlined,
                    ),
                    title:
                    Text(
                      producto['nombre']!,
                    ),
                    trailing:
                    const Icon(
                      Icons.chevron_right,
                    ),
                    onTap: () {
                      _mostrarDetalle(
                        producto,
                      );
                    },
                  );
                },
              ),
            ),
          ),

          const SizedBox(
            height: 16,
          ),

          // 5. DESLIZAR PARA ELIMINAR
          ElementoDemo(
            titulo:
            'Deslizar para eliminar y estado vacío',
            descripcion:
            'Dismissible permite eliminar elementos mediante un gesto lateral. '
                'Cuando la lista queda vacía se muestra un estado alternativo con mensaje e ilustración.',
            contenido:
            _tareas.isEmpty
                ? _buildEstadoVacio()
                : SizedBox(
              height:
              280,
              child:
              ListView.builder(
                itemCount:
                _tareas.length,
                itemBuilder:
                    (context, index) {

                  final tarea =
                  _tareas[index];

                  return Dismissible(
                    key:
                    ValueKey(
                      tarea,
                    ),
                    direction:
                    DismissDirection.horizontal,
                    background:
                    Container(
                      color:
                      Theme.of(context)
                          .colorScheme
                          .errorContainer,
                      alignment:
                      Alignment.centerLeft,
                      padding:
                      const EdgeInsets.only(
                        left:
                        20,
                      ),
                      child:
                      const Icon(
                        Icons.delete,
                      ),
                    ),
                    secondaryBackground:
                    Container(
                      color:
                      Theme.of(context)
                          .colorScheme
                          .errorContainer,
                      alignment:
                      Alignment.centerRight,
                      padding:
                      const EdgeInsets.only(
                        right:
                        20,
                      ),
                      child:
                      const Icon(
                        Icons.delete,
                      ),
                    ),
                    onDismissed:
                        (_) {

                      setState(() {
                        _tareas.remove(
                          tarea,
                        );
                      });

                      ScaffoldMessenger.of(
                        context,
                      ).showSnackBar(
                        SnackBar(
                          content:
                          Text(
                            '$tarea eliminada',
                          ),
                        ),
                      );
                    },
                    child:
                    ListTile(
                      leading:
                      const Icon(
                        Icons.task_alt,
                      ),
                      title:
                      Text(
                        tarea,
                      ),
                      subtitle:
                      const Text(
                        'Desliza a la izquierda o derecha',
                      ),
                    ),
                  );
                },
              ),
            ),
          ),

          const SizedBox(
            height: 16,
          ),

          // 6. PULL TO REFRESH
          ElementoDemo(
            titulo:
            'Actualizar arrastrando hacia abajo',
            descripcion:
            'RefreshIndicator detecta el gesto de arrastrar hacia abajo y ejecuta una actualización de los datos mostrados.',
            contenido: Column(
              crossAxisAlignment:
              CrossAxisAlignment.start,
              children: [

                Text(
                  'Actualizaciones recibidas: '
                      '$_actualizaciones',
                ),

                const SizedBox(
                  height: 8,
                ),

                SizedBox(
                  height:
                  260,
                  child:
                  RefreshIndicator(
                    onRefresh:
                    _actualizarLista,
                    child:
                    ListView.builder(
                      physics:
                      const AlwaysScrollableScrollPhysics(),
                      itemCount:
                      _registros.length,
                      itemBuilder:
                          (context, index) {

                        return ListTile(
                          leading:
                          const Icon(
                            Icons.refresh,
                          ),
                          title:
                          Text(
                            _registros[index],
                          ),
                        );
                      },
                    ),
                  ),
                ),
              ],
            ),
          ),

          const SizedBox(
            height: 16,
          ),

          // 7. PESTAÑAS
          ElementoDemo(
            titulo:
            'Pestañas con contenido deslizable',
            descripcion:
            'TabBar y TabBarView permiten cambiar de contenido tocando una pestaña o deslizando horizontalmente.',
            contenido:
            const _DemoPestanas(),
          ),

          const SizedBox(
            height: 24,
          ),
        ],
      ),
    );
  }

  Widget _buildEstadoVacio() {

    return SizedBox(
      height:
      280,
      child:
      Center(
        child:
        Column(
          mainAxisAlignment:
          MainAxisAlignment.center,
          children: [

            Icon(
              Icons.inbox_outlined,
              size:
              64,
              color:
              Theme.of(context)
                  .colorScheme
                  .primary,
            ),

            const SizedBox(
              height: 12,
            ),

            Text(
              'No hay elementos',
              style:
              Theme.of(context)
                  .textTheme
                  .titleMedium
                  ?.copyWith(
                fontWeight:
                FontWeight.bold,
              ),
            ),

            const SizedBox(
              height: 6,
            ),

            const Text(
              'Has eliminado todas las tareas.',
            ),

            const SizedBox(
              height: 14,
            ),

            FilledButton.icon(
              onPressed:
              _reiniciarTareas,
              icon:
              const Icon(
                Icons.restart_alt,
              ),
              label:
              const Text(
                'Reiniciar lista',
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class _ElementoSeccion {
  final String texto;
  final bool encabezado;

  const _ElementoSeccion({
    required this.texto,
    this.encabezado = false,
  });
}

class _DemoPestanas extends StatelessWidget {
  const _DemoPestanas();

  @override
  Widget build(
      BuildContext context,
      ) {

    return DefaultTabController(
      length:
      3,
      child:
      Column(
        children: [

          const TabBar(
            tabs: [
              Tab(
                text:
                'Tab 1',
              ),
              Tab(
                text:
                'Tab 2',
              ),
              Tab(
                text:
                'Tab 3',
              ),
            ],
          ),

          const SizedBox(
            height: 8,
          ),

          SizedBox(
            height:
            180,
            child:
            TabBarView(
              children: [

                _ContenidoTab(
                  icono:
                  Icons.looks_one,
                  texto:
                  'Contenido de la pestaña 1',
                ),

                _ContenidoTab(
                  icono:
                  Icons.looks_two,
                  texto:
                  'Contenido de la pestaña 2',
                ),

                _ContenidoTab(
                  icono:
                  Icons.looks_3,
                  texto:
                  'Contenido de la pestaña 3',
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

class _ContenidoTab extends StatelessWidget {
  final IconData icono;
  final String texto;

  const _ContenidoTab({
    required this.icono,
    required this.texto,
  });

  @override
  Widget build(
      BuildContext context,
      ) {

    return Center(
      child:
      Column(
        mainAxisAlignment:
        MainAxisAlignment.center,
        children: [

          Icon(
            icono,
            size:
            48,
          ),

          const SizedBox(
            height: 12,
          ),

          Text(
            texto,
            textAlign:
            TextAlign.center,
          ),
        ],
      ),
    );
  }
}