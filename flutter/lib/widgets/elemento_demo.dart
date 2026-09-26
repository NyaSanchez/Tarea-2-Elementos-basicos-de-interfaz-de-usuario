import 'package:flutter/material.dart';

class ElementoDemo extends StatelessWidget {
  final String titulo;
  final String descripcion;
  final Widget contenido;

  const ElementoDemo({
    super.key,
    required this.titulo,
    required this.descripcion,
    required this.contenido,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(titulo, style: Theme.of(context).textTheme.titleMedium),
            Padding(
              padding: const EdgeInsets.only(top: 4, bottom: 12),
              child: Text(descripcion, style: Theme.of(context).textTheme.bodySmall),
            ),
            const Divider(),
            const SizedBox(height: 8),
            contenido,
          ],
        ),
      ),
    );
  }
}