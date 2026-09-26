import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

import 'package:elementos_ui/main.dart';

void main() {
  testWidgets('La app carga la pantalla principal', (WidgetTester tester) async {
    await tester.pumpWidget(const ElementosUIApp());

    expect(find.text('Elementos de la Interfaz'), findsOneWidget);
  });
}