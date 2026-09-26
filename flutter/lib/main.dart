import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:flutter/material.dart';
import 'screens/home_screen.dart';

void main() {
  runApp(const ElementosUIApp());
}

class ElementosUIApp extends StatelessWidget {
  const ElementosUIApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Elementos Básicos de la Interfaz de Usuario',
      debugShowCheckedModeBanner: false,

      locale: const Locale('es', 'MX'),
      localizationsDelegates: GlobalMaterialLocalizations.delegates,
      supportedLocales: const [
        Locale('es'),
        Locale('es', 'MX'),
      ],

      theme: ThemeData(
        colorSchemeSeed: Colors.teal,
        brightness: Brightness.light,
      ),
      darkTheme: ThemeData(
        colorSchemeSeed: Colors.teal,
        brightness: Brightness.dark,
      ),
      themeMode: ThemeMode.system,
      home: const HomeScreen(),
    );
  }
}