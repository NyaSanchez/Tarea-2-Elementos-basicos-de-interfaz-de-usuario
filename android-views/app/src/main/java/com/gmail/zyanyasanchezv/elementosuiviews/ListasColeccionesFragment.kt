package com.gmail.zyanyasanchezv.elementosuiviews

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.zyanyasanchezv.elementosuiviews.databinding.FragmentListasColeccionesBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator

class ListasColeccionesFragment : Fragment() {

    private var _binding: FragmentListasColeccionesBinding? = null
    private val binding get() = _binding!!

    private var tabMediator: TabLayoutMediator? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =
            FragmentListasColeccionesBinding.inflate(
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

        configurarListaVertical()
        configurarGrid()
        configurarEncabezados()
        configurarDetalle()
        configurarEliminar()
        configurarRefresco()
        configurarPestanas()
    }

    private fun configurarListaVertical() {

        val elementos =
            (1..20).map {
                "Elemento $it"
            } + DatosCompartidos.elementosLista

        binding.rvListaVertical.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvListaVertical.adapter =
            TextoAdapter(elementos)
    }

    private fun configurarGrid() {

        val elementos =
            (1..12).map {
                "Elemento $it"
            }

        binding.rvGrid.layoutManager =
            GridLayoutManager(
                requireContext(),
                3
            )

        binding.rvGrid.adapter =
            TextoAdapter(
                elementos,
                centrado = true,
                altoDp = 72
            )
    }

    private fun configurarEncabezados() {

        val elementos = listOf(
            ElementoSeccion("Frutas", true),
            ElementoSeccion("Manzana", false),
            ElementoSeccion("Plátano", false),
            ElementoSeccion("Naranja", false),
            ElementoSeccion("Uva", false),

            ElementoSeccion("Verduras", true),
            ElementoSeccion("Zanahoria", false),
            ElementoSeccion("Brócoli", false),
            ElementoSeccion("Espinaca", false),
            ElementoSeccion("Papa", false)
        )

        binding.rvSecciones.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvSecciones.adapter =
            SeccionesAdapter(elementos)
    }

    private fun configurarDetalle() {

        val productos = listOf(
            "Producto A" to
                    "Disponible. Envío estimado en dos días.",

            "Producto B" to
                    "Agotado temporalmente.",

            "Producto C" to
                    "Producto en oferta durante esta semana.",

            "Producto D" to
                    "Nuevo ingreso al catálogo."
        )

        binding.rvProductos.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvProductos.adapter =
            TextoAdapter(
                productos.map { it.first }
            ) { nombre ->

                val descripcion =
                    productos.first {
                        it.first == nombre
                    }.second

                MaterialAlertDialogBuilder(
                    requireContext()
                )
                    .setTitle(nombre)
                    .setMessage(descripcion)
                    .setPositiveButton(
                        "Cerrar",
                        null
                    )
                    .show()
            }
    }

    private fun configurarEliminar() {

        val originales =
            (1..5).map {
                "Tarea $it"
            }

        val elementos =
            originales.toMutableList()

        val adapter =
            MutableTextoAdapter(elementos)

        binding.rvEliminar.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvEliminar.adapter =
            adapter

        fun actualizarEstadoVacio() {

            val vacio =
                adapter.estaVacia()

            binding.estadoVacio.isVisible =
                vacio

            binding.rvEliminar.isVisible =
                !vacio
        }

        val helper =
            ItemTouchHelper(
                object :
                    ItemTouchHelper.SimpleCallback(
                        0,
                        ItemTouchHelper.LEFT or
                                ItemTouchHelper.RIGHT
                    ) {

                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        return false
                    }

                    override fun onSwiped(
                        viewHolder: RecyclerView.ViewHolder,
                        direction: Int
                    ) {

                        val posicion =
                            viewHolder.bindingAdapterPosition

                        if (
                            posicion !=
                            RecyclerView.NO_POSITION
                        ) {

                            adapter.eliminar(
                                posicion
                            )

                            actualizarEstadoVacio()
                        }
                    }
                }
            )

        helper.attachToRecyclerView(
            binding.rvEliminar
        )

        binding.btnReiniciar.setOnClickListener {

            adapter.restaurar(
                originales
            )

            actualizarEstadoVacio()
        }

        actualizarEstadoVacio()
    }

    private fun configurarRefresco() {

        var actualizaciones = 0

        val registros =
            (1..10)
                .map {
                    "Registro $it"
                }
                .toMutableList()

        val adapter =
            MutableTextoAdapter(registros)

        binding.rvRefresco.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvRefresco.adapter =
            adapter

        binding.swipeRefresh.setOnRefreshListener {

            binding.swipeRefresh.postDelayed(
                {

                    if (_binding != null) {

                        actualizaciones++

                        adapter.agregarInicio(
                            "Nuevo registro $actualizaciones"
                        )

                        binding.tvActualizaciones.text =
                            "Actualizaciones recibidas: $actualizaciones"

                        binding.swipeRefresh.isRefreshing =
                            false
                    }

                },
                1000
            )
        }
    }

    private fun configurarPestanas() {

        val contenidos = listOf(
            "Contenido de la pestaña 1",
            "Contenido de la pestaña 2",
            "Contenido de la pestaña 3"
        )

        binding.viewPager.adapter =
            PagerAdapter(contenidos)

        tabMediator =
            TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager
            ) { tab, position ->

                tab.text =
                    "Tab ${position + 1}"
            }

        tabMediator?.attach()
    }

    data class ElementoSeccion(
        val texto: String,
        val encabezado: Boolean
    )

    private class TextoAdapter(
        private val elementos: List<String>,
        private val centrado: Boolean = false,
        private val altoDp: Int? = null,
        private val onClick: ((String) -> Unit)? = null
    ) :
        RecyclerView.Adapter<TextoAdapter.Holder>() {

        class Holder(
            val texto: TextView
        ) : RecyclerView.ViewHolder(texto)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): Holder {

            val densidad =
                parent.resources
                    .displayMetrics
                    .density

            val padding =
                (14 * densidad).toInt()

            val texto =
                TextView(parent.context).apply {

                    textSize = 16f

                    setPadding(
                        padding,
                        padding,
                        padding,
                        padding
                    )

                    if (centrado) {
                        gravity = Gravity.CENTER
                    }

                    layoutParams =
                        RecyclerView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            if (altoDp != null) {
                                (altoDp * densidad).toInt()
                            } else {
                                ViewGroup.LayoutParams.WRAP_CONTENT
                            }
                        )
                }

            return Holder(texto)
        }

        override fun onBindViewHolder(
            holder: Holder,
            position: Int
        ) {

            val elemento =
                elementos[position]

            holder.texto.text =
                elemento

            holder.texto.setOnClickListener {

                onClick?.invoke(
                    elemento
                )
            }
        }

        override fun getItemCount(): Int =
            elementos.size
    }

    private class MutableTextoAdapter(
        private val elementos:
        MutableList<String>
    ) :
        RecyclerView.Adapter<
                MutableTextoAdapter.Holder>() {

        class Holder(
            val texto: TextView
        ) : RecyclerView.ViewHolder(texto)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): Holder {

            val densidad =
                parent.resources
                    .displayMetrics
                    .density

            val padding =
                (14 * densidad).toInt()

            val texto =
                TextView(parent.context).apply {

                    textSize = 16f

                    setPadding(
                        padding,
                        padding,
                        padding,
                        padding
                    )

                    layoutParams =
                        RecyclerView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                }

            return Holder(texto)
        }

        override fun onBindViewHolder(
            holder: Holder,
            position: Int
        ) {

            holder.texto.text =
                elementos[position]
        }

        override fun getItemCount(): Int =
            elementos.size

        fun eliminar(
            posicion: Int
        ) {

            elementos.removeAt(
                posicion
            )

            notifyItemRemoved(
                posicion
            )
        }

        fun restaurar(
            nuevos: List<String>
        ) {

            elementos.clear()
            elementos.addAll(nuevos)

            notifyDataSetChanged()
        }

        fun agregarInicio(
            elemento: String
        ) {

            elementos.add(
                0,
                elemento
            )

            notifyItemInserted(0)
        }

        fun estaVacia(): Boolean =
            elementos.isEmpty()
    }

    private class SeccionesAdapter(
        private val elementos:
        List<ElementoSeccion>
    ) :
        RecyclerView.Adapter<
                SeccionesAdapter.Holder>() {

        class Holder(
            val texto: TextView
        ) : RecyclerView.ViewHolder(texto)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): Holder {

            val densidad =
                parent.resources
                    .displayMetrics
                    .density

            val padding =
                (10 * densidad).toInt()

            val texto =
                TextView(parent.context).apply {

                    setPadding(
                        padding,
                        padding,
                        padding,
                        padding
                    )

                    layoutParams =
                        RecyclerView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                }

            return Holder(texto)
        }

        override fun onBindViewHolder(
            holder: Holder,
            position: Int
        ) {

            val elemento =
                elementos[position]

            holder.texto.text =
                elemento.texto

            if (elemento.encabezado) {

                holder.texto.textSize =
                    18f

                holder.texto.setTypeface(
                    null,
                    Typeface.BOLD
                )

            } else {

                holder.texto.textSize =
                    16f

                holder.texto.setTypeface(
                    null,
                    Typeface.NORMAL
                )
            }
        }

        override fun getItemCount(): Int =
            elementos.size
    }

    private class PagerAdapter(
        private val contenidos:
        List<String>
    ) :
        RecyclerView.Adapter<
                PagerAdapter.Holder>() {

        class Holder(
            val texto: TextView
        ) : RecyclerView.ViewHolder(texto)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): Holder {

            val texto =
                TextView(parent.context).apply {

                    gravity =
                        Gravity.CENTER

                    textSize =
                        18f

                    layoutParams =
                        RecyclerView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                }

            return Holder(texto)
        }

        override fun onBindViewHolder(
            holder: Holder,
            position: Int
        ) {

            holder.texto.text =
                contenidos[position]
        }

        override fun getItemCount(): Int =
            contenidos.size
    }

    override fun onDestroyView() {

        tabMediator?.detach()
        tabMediator = null

        super.onDestroyView()
        _binding = null
    }
}