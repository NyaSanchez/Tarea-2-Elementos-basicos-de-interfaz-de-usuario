import 'package:flutter/material.dart';

class SeccionPlaceholderScreen extends StatelessWidget {
  final String titulo;

  const SeccionPlaceholderScreen({super.key, required this.titulo});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(
        '$titulo\n(en construcción)',
        textAlign: TextAlign.center,
      ),
    );
  }
}