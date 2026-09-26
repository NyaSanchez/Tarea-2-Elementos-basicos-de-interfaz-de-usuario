package com.gmail.zyanyasanchezv.elementosuiviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.gmail.zyanyasanchezv.elementosuiviews.databinding.FragmentContenedoresBinding

class ContenedoresFragment : Fragment() {

    private var _binding: FragmentContenedoresBinding? = null
    private val binding get() = _binding!!

    private var superpuestoVisible = true
    private var pesosAlternos = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =
            FragmentContenedoresBinding.inflate(
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

        configurarFila()
        configurarColumna()
        configurarSuperposicion()
        configurarToolbar()
        configurarNavegacionInferior()
        configurarPesos()
    }

    private fun configurarFila() {

        binding.btnFilaUno.setOnClickListener {
            binding.tvFila.text =
                "Seleccionaste el elemento Uno"
        }

        binding.btnFilaDos.setOnClickListener {
            binding.tvFila.text =
                "Seleccionaste el elemento Dos"
        }

        binding.btnFilaTres.setOnClickListener {
            binding.tvFila.text =
                "Seleccionaste el elemento Tres"
        }
    }

    private fun configurarColumna() {

        binding.btnColumnaA.setOnClickListener {
            binding.tvColumna.text =
                "Seleccionaste el elemento A"
        }

        binding.btnColumnaB.setOnClickListener {
            binding.tvColumna.text =
                "Seleccionaste el elemento B"
        }

        binding.btnColumnaC.setOnClickListener {
            binding.tvColumna.text =
                "Seleccionaste el elemento C"
        }
    }

    private fun configurarSuperposicion() {

        binding.btnSuperpuesto.setOnClickListener {

            superpuestoVisible =
                !superpuestoVisible

            binding.tvSuperpuesto.visibility =
                if (superpuestoVisible) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
        }
    }

    private fun configurarToolbar() {

        val buscar =
            binding.toolbarDemo.menu.add(
                Menu.NONE,
                1,
                Menu.NONE,
                "Buscar"
            )

        buscar.setIcon(
            android.R.drawable.ic_menu_search
        )

        buscar.setShowAsAction(
            MenuItem.SHOW_AS_ACTION_ALWAYS
        )

        val favorito =
            binding.toolbarDemo.menu.add(
                Menu.NONE,
                2,
                Menu.NONE,
                "Favorito"
            )

        favorito.setIcon(
            android.R.drawable.btn_star_big_off
        )

        favorito.setShowAsAction(
            MenuItem.SHOW_AS_ACTION_ALWAYS
        )

        binding.toolbarDemo.setOnMenuItemClickListener {
                item ->

            when (item.itemId) {

                1 -> {
                    binding.tvToolbarAccion.text =
                        "Acción seleccionada: Buscar"
                    true
                }

                2 -> {
                    binding.tvToolbarAccion.text =
                        "Acción seleccionada: Favorito"
                    true
                }

                else -> false
            }
        }
    }

    private fun configurarNavegacionInferior() {

        val menu =
            binding.bottomNavigationDemo.menu

        menu.add(
            Menu.NONE,
            10,
            Menu.NONE,
            "Inicio"
        ).setIcon(
            android.R.drawable.ic_menu_view
        )

        menu.add(
            Menu.NONE,
            11,
            Menu.NONE,
            "Buscar"
        ).setIcon(
            android.R.drawable.ic_menu_search
        )

        menu.add(
            Menu.NONE,
            12,
            Menu.NONE,
            "Perfil"
        ).setIcon(
            android.R.drawable.ic_menu_myplaces
        )

        binding.bottomNavigationDemo.selectedItemId =
            10

        binding.bottomNavigationDemo
            .setOnItemSelectedListener {
                    item ->

                binding.tvNavegacion.text =
                    when (item.itemId) {

                        10 ->
                            "Destino actual: Inicio"

                        11 ->
                            "Destino actual: Buscar"

                        12 ->
                            "Destino actual: Perfil"

                        else ->
                            "Destino actual"
                    }

                true
            }
    }

    private fun configurarPesos() {

        binding.btnCambiarPesos.setOnClickListener {

            pesosAlternos =
                !pesosAlternos

            val parametrosUno =
                binding.bloqueUno.layoutParams
                        as LinearLayout.LayoutParams

            val parametrosDos =
                binding.bloqueDos.layoutParams
                        as LinearLayout.LayoutParams

            if (pesosAlternos) {

                parametrosUno.weight =
                    2f

                parametrosDos.weight =
                    1f

                binding.tvPesos.text =
                    "Proporción actual: 2 : 1"

            } else {

                parametrosUno.weight =
                    1f

                parametrosDos.weight =
                    2f

                binding.tvPesos.text =
                    "Proporción actual: 1 : 2"
            }

            binding.bloqueUno.layoutParams =
                parametrosUno

            binding.bloqueDos.layoutParams =
                parametrosDos
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}