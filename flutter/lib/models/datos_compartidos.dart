import 'package:flutter/foundation.dart';

class DatosCompartidos {
  static final ValueNotifier<List<String>> elementos =
  ValueNotifier<List<String>>([]);

  static void agregar(String elemento) {
    elementos.value = [
      ...elementos.value,
      elemento,
    ];
  }
}