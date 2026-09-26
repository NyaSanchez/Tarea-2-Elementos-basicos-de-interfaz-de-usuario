package com.gmail.zyanyasanchezv.elementosuiviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.zyanyasanchezv.elementosuiviews.databinding.FragmentBotonesAccionesBinding

class BotonesAccionesFragment : Fragment() {

    private var _binding: FragmentBotonesAccionesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =
            FragmentBotonesAccionesBinding.inflate(
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

        configurarBotonesBasicos()
        configurarBotonesConIcono()
        configurarFab()
        configurarSelectorSegmentado()
        configurarBotonCarga()
    }

    private fun mostrarRespuesta(mensaje: String) {
        binding.tvRespuesta.text = "Respuesta: $mensaje"
    }

    private fun configurarBotonesBasicos() {

        binding.btnRelleno.setOnClickListener {
            mostrarRespuesta("Pulsaste el botón relleno")
        }

        binding.btnContorno.setOnClickListener {
            mostrarRespuesta("Pulsaste el botón con contorno")
        }

        binding.btnTexto.setOnClickListener {
            mostrarRespuesta("Pulsaste el botón de solo texto")
        }
    }

    private fun configurarBotonesConIcono() {

        binding.btnSoloIcono.setOnClickListener {
            mostrarRespuesta("Pulsaste el botón de solo ícono")
        }

        binding.btnIconoTexto.setOnClickListener {
            mostrarRespuesta("Pulsaste el botón con ícono y texto")
        }
    }

    private fun configurarFab() {

        binding.fabNormal.setOnClickListener {
            mostrarRespuesta("Pulsaste el FAB normal")
        }

        binding.fabExtendido.setOnClickListener {
            mostrarRespuesta("Pulsaste el FAB extendido")
        }
    }

    private fun configurarSelectorSegmentado() {

        binding.toggleGroup.check(
            R.id.btnSegmentoUno
        )

        binding.toggleGroup.addOnButtonCheckedListener {
                _,
                checkedId,
                isChecked ->

            if (isChecked) {

                val opcion =
                    when (checkedId) {

                        R.id.btnSegmentoUno ->
                            "Uno"

                        R.id.btnSegmentoDos ->
                            "Dos"

                        R.id.btnSegmentoTres ->
                            "Tres"

                        else ->
                            "Sin selección"
                    }

                mostrarRespuesta(
                    "Seleccionaste la opción $opcion"
                )
            }
        }
    }

    private fun configurarBotonCarga() {

        binding.btnCarga.setOnClickListener {

            binding.btnCarga.isEnabled = false
            binding.btnCarga.text = "Cargando..."

            binding.progressCarga.visibility =
                View.VISIBLE

            mostrarRespuesta(
                "La operación está en proceso"
            )

            binding.btnCarga.postDelayed(
                {

                    if (_binding != null) {

                        binding.progressCarga.visibility =
                            View.GONE

                        binding.btnCarga.isEnabled = true
                        binding.btnCarga.text =
                            "Iniciar carga"

                        mostrarRespuesta(
                            "La operación terminó correctamente"
                        )
                    }

                },
                1500
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}