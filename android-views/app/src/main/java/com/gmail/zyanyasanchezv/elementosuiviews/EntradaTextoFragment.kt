package com.gmail.zyanyasanchezv.elementosuiviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.gmail.zyanyasanchezv.elementosuiviews.databinding.FragmentEntradaTextoBinding

class EntradaTextoFragment : Fragment() {

    private var _binding: FragmentEntradaTextoBinding? = null
    private val binding get() = _binding!!

    private fun configurarConexionSecciones() {

        binding.btnAgregarALista.setOnClickListener {

            val nombre =
                binding.etNombre.text
                    ?.toString()
                    ?.trim()
                    .orEmpty()

            if (nombre.isBlank()) {

                binding.tvConexion.text =
                    "Primero escribe un nombre."

                return@setOnClickListener
            }

            DatosCompartidos.elementosLista.add(
                nombre
            )

            binding.tvConexion.text =
                "\"$nombre\" se agregó a la lista de la Sección 4."

            binding.etNombre.text?.clear()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =
            FragmentEntradaTextoBinding.inflate(
                inflater,
                container,
                false
            )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        configurarValidacion()
        configurarDesplegable()
        configurarBusqueda()
        configurarConexionSecciones()
    }

    private fun configurarValidacion() {

        binding.etUsuario.doAfterTextChanged { texto ->

            val usuario = texto.toString()

            binding.tilUsuario.error =
                if (
                    usuario.isNotEmpty() &&
                    usuario.length < 4
                ) {
                    "Debe tener al menos 4 caracteres"
                } else {
                    null
                }
        }
    }

    private fun configurarDesplegable() {

        val opciones = listOf(
            "Opción A",
            "Opción B",
            "Opción C",
            "Opción D"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            opciones
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spinnerOpciones.adapter = adapter

        binding.spinnerOpciones.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    binding.tvOpcionSeleccionada.text =
                        "Opción seleccionada: ${opciones[position]}"
                }

                override fun onNothingSelected(
                    parent: AdapterView<*>?
                ) {
                    // No es necesario realizar ninguna acción.
                }
            }
    }

    private fun configurarBusqueda() {

        val secciones = listOf(
            "Entrada de texto",
            "Botones y acciones",
            "Elementos de selección",
            "Listas y colecciones",
            "Información y retroalimentación",
            "Contenedores y estructura"
        )

        mostrarResultados(secciones)

        binding.searchView.setOnQueryTextListener(
            object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(
                    query: String?
                ): Boolean {

                    return false
                }

                override fun onQueryTextChange(
                    newText: String?
                ): Boolean {

                    val texto =
                        newText.orEmpty()

                    val resultados =
                        secciones.filter {
                            it.contains(
                                texto,
                                ignoreCase = true
                            )
                        }

                    mostrarResultados(resultados)

                    return true
                }
            }
        )
    }

    private fun mostrarResultados(
        resultados: List<String>
    ) {

        binding.tvResultadosBusqueda.text =
            if (resultados.isEmpty()) {

                "Sin resultados"

            } else {

                resultados.joinToString(
                    separator = "\n"
                ) {
                    "• $it"
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}