package com.gmail.zyanyasanchezv.elementosuiviews

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.gmail.zyanyasanchezv.elementosuiviews.databinding.FragmentSeleccionBinding
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class SeleccionFragment : Fragment() {

    private var _binding: FragmentSeleccionBinding? = null
    private val binding get() = _binding!!

    private var estadoTri = MaterialCheckBox.STATE_UNCHECKED

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =
            FragmentSeleccionBinding.inflate(
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

        configurarCheckbox()
        configurarRadioButtons()
        configurarSwitch()
        configurarSliders()
        configurarSpinner()
        configurarFecha()
        configurarHora()
        configurarChips()
    }

    private fun configurarCheckbox() {

        binding.checkNormal.setOnCheckedChangeListener {
                _,
                isChecked ->

            binding.tvEstadoCheckNormal.text =
                if (isChecked) {
                    "Estado: marcado"
                } else {
                    "Estado: desmarcado"
                }
        }

        actualizarTriEstado()

        binding.btnCambiarTriEstado.setOnClickListener {

            estadoTri =
                when (estadoTri) {

                    MaterialCheckBox.STATE_UNCHECKED ->
                        MaterialCheckBox.STATE_CHECKED

                    MaterialCheckBox.STATE_CHECKED ->
                        MaterialCheckBox.STATE_INDETERMINATE

                    else ->
                        MaterialCheckBox.STATE_UNCHECKED
                }

            actualizarTriEstado()
        }
    }

    private fun actualizarTriEstado() {

        binding.checkTriEstado.checkedState =
            estadoTri

        binding.tvEstadoTri.text =
            when (estadoTri) {

                MaterialCheckBox.STATE_CHECKED ->
                    "Estado: marcado"

                MaterialCheckBox.STATE_INDETERMINATE ->
                    "Estado: indeterminado"

                else ->
                    "Estado: desmarcado"
            }
    }

    private fun configurarRadioButtons() {

        binding.radioGroup.setOnCheckedChangeListener {
                _,
                checkedId ->

            binding.tvRadioSeleccion.text =
                when (checkedId) {

                    R.id.radioAndroid ->
                        "Selección: Android"

                    R.id.radioFlutter ->
                        "Selección: Flutter"

                    R.id.radioOtro ->
                        "Selección: Otro"

                    else ->
                        "Selección: ninguna"
                }
        }
    }

    private fun configurarSwitch() {

        binding.switchModo.setOnCheckedChangeListener {
                _,
                isChecked ->

            binding.tvEstadoSwitch.text =
                if (isChecked) {
                    "Estado: activado"
                } else {
                    "Estado: desactivado"
                }
        }
    }

    private fun configurarSliders() {

        binding.sliderValor.addOnChangeListener {
                _,
                value,
                _ ->

            binding.tvValorSlider.text =
                "Valor: ${value.toInt()}"
        }

        binding.rangeSlider.setValues(
            20f,
            80f
        )

        binding.rangeSlider.addOnChangeListener {
                _,
                _,
                _ ->

            val valores =
                binding.rangeSlider.values

            binding.tvValorRango.text =
                "Rango: ${valores[0].toInt()} - ${valores[1].toInt()}"
        }
    }

    private fun configurarSpinner() {

        val opciones = listOf(
            "México",
            "Canadá",
            "España",
            "Japón"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            opciones
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spinnerSeleccion.adapter =
            adapter

        binding.spinnerSeleccion.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    binding.tvSpinnerSeleccion.text =
                        "Selección: ${opciones[position]}"
                }

                override fun onNothingSelected(
                    parent: AdapterView<*>?
                ) {
                    // No es necesario realizar ninguna acción.
                }
            }
    }

    private fun configurarFecha() {

        binding.btnFecha.setOnClickListener {

            val picker =
                MaterialDatePicker.Builder
                    .datePicker()
                    .setTitleText("Selecciona una fecha")
                    .setSelection(
                        MaterialDatePicker.todayInUtcMilliseconds()
                    )
                    .build()

            picker.addOnPositiveButtonClickListener {
                    seleccion ->

                val formato =
                    SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale.getDefault()
                    )

                formato.timeZone =
                    TimeZone.getTimeZone("UTC")

                binding.tvFecha.text =
                    "Fecha: ${formato.format(seleccion)}"
            }

            picker.show(
                parentFragmentManager,
                "selector_fecha"
            )
        }
    }

    private fun configurarHora() {

        binding.btnHora.setOnClickListener {

            val ahora =
                Calendar.getInstance()

            val formato24Horas =
                DateFormat.is24HourFormat(
                    requireContext()
                )

            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(
                        if (formato24Horas) {
                            TimeFormat.CLOCK_24H
                        } else {
                            TimeFormat.CLOCK_12H
                        }
                    )
                    .setHour(
                        ahora.get(Calendar.HOUR_OF_DAY)
                    )
                    .setMinute(
                        ahora.get(Calendar.MINUTE)
                    )
                    .setTitleText(
                        "Selecciona una hora"
                    )
                    .build()

            picker.addOnPositiveButtonClickListener {

                val hora =
                    String.format(
                        Locale.getDefault(),
                        "%02d:%02d",
                        picker.hour,
                        picker.minute
                    )

                binding.tvHora.text =
                    "Hora: $hora"
            }

            picker.show(
                parentFragmentManager,
                "selector_hora"
            )
        }
    }

    private fun configurarChips() {

        val actualizar = {

            val seleccionados =
                mutableListOf<String>()

            if (binding.chipAndroid.isChecked) {
                seleccionados.add("Android")
            }

            if (binding.chipKotlin.isChecked) {
                seleccionados.add("Kotlin")
            }

            if (binding.chipFlutter.isChecked) {
                seleccionados.add("Flutter")
            }

            binding.tvChips.text =
                if (seleccionados.isEmpty()) {

                    "Filtros: ninguno"

                } else {

                    "Filtros: ${
                        seleccionados.joinToString(", ")
                    }"
                }
        }

        binding.chipAndroid.setOnCheckedChangeListener {
                _,
                _ ->
            actualizar()
        }

        binding.chipKotlin.setOnCheckedChangeListener {
                _,
                _ ->
            actualizar()
        }

        binding.chipFlutter.setOnCheckedChangeListener {
                _,
                _ ->
            actualizar()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}