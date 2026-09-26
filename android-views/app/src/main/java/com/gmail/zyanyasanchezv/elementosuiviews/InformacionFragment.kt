package com.gmail.zyanyasanchezv.elementosuiviews

import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import coil.load
import com.gmail.zyanyasanchezv.elementosuiviews.databinding.FragmentInformacionBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class InformacionFragment : Fragment() {

    private var _binding: FragmentInformacionBinding? = null
    private val binding get() = _binding!!

    private var mostrandoIndeterminado = false
    private var notificaciones = 3

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =
            FragmentInformacionBinding.inflate(
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

        configurarTipografia()
        configurarImagenes()
        configurarProgreso()
        configurarMensajes()
        configurarDialogo()
        configurarBottomSheet()
        configurarBadge()
    }

    private fun configurarTipografia() {

        binding.tvSubrayado.paintFlags =
            binding.tvSubrayado.paintFlags or
                    Paint.UNDERLINE_TEXT_FLAG

        binding.btnTextoTitulo.setOnClickListener {
            aplicarEstiloTexto(
                nombre = "Título",
                tamano = 26f,
                estilo = Typeface.BOLD
            )
        }

        binding.btnTextoSubtitulo.setOnClickListener {
            aplicarEstiloTexto(
                nombre = "Subtítulo",
                tamano = 20f,
                estilo = Typeface.BOLD
            )
        }

        binding.btnTextoCuerpo.setOnClickListener {
            aplicarEstiloTexto(
                nombre = "Cuerpo",
                tamano = 16f,
                estilo = Typeface.NORMAL
            )
        }

        binding.btnTextoEtiqueta.setOnClickListener {
            aplicarEstiloTexto(
                nombre = "Etiqueta",
                tamano = 12f,
                estilo = Typeface.NORMAL
            )
        }
    }

    private fun aplicarEstiloTexto(
        nombre: String,
        tamano: Float,
        estilo: Int
    ) {

        binding.tvTextoEjemplo.text =
            "Texto de ejemplo: $nombre"

        binding.tvTextoEjemplo.setTextSize(
            TypedValue.COMPLEX_UNIT_SP,
            tamano
        )

        binding.tvTextoEjemplo.setTypeface(
            null,
            estilo
        )
    }

    private fun configurarImagenes() {

        binding.ivUrl.load(
            "https://picsum.photos/400/300"
        )

        binding.btnCrop.setOnClickListener {
            cambiarEscala(
                ImageView.ScaleType.CENTER_CROP,
                "Recortar"
            )
        }

        binding.btnFit.setOnClickListener {
            cambiarEscala(
                ImageView.ScaleType.FIT_CENTER,
                "Ajustar"
            )
        }

        binding.btnFill.setOnClickListener {
            cambiarEscala(
                ImageView.ScaleType.FIT_XY,
                "Rellenar"
            )
        }
    }

    private fun cambiarEscala(
        escala: ImageView.ScaleType,
        nombre: String
    ) {

        binding.ivLocal.scaleType =
            escala

        binding.ivUrl.scaleType =
            escala

        binding.tvModoEscala.text =
            "Modo de escalado: $nombre"
    }

    private fun configurarProgreso() {

        binding.progressLineal.setProgressCompat(
            40,
            false
        )

        binding.progressCircular.setProgressCompat(
            40,
            false
        )

        binding.seekProgreso.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {

                    binding.tvProgreso.text =
                        "Progreso: $progress%"

                    binding.progressLineal.setProgressCompat(
                        progress,
                        true
                    )

                    binding.progressCircular.setProgressCompat(
                        progress,
                        true
                    )
                }

                override fun onStartTrackingTouch(
                    seekBar: SeekBar?
                ) {
                    // No se requiere acción.
                }

                override fun onStopTrackingTouch(
                    seekBar: SeekBar?
                ) {
                    // No se requiere acción.
                }
            }
        )

        binding.btnIndeterminado.setOnClickListener {

            mostrandoIndeterminado =
                !mostrandoIndeterminado

            binding.progressLinealIndeterminado.isVisible =
                mostrandoIndeterminado

            binding.progressCircularIndeterminado.isVisible =
                mostrandoIndeterminado

            binding.btnIndeterminado.text =
                if (mostrandoIndeterminado) {
                    "Ocultar indeterminado"
                } else {
                    "Mostrar indeterminado"
                }
        }
    }

    private fun configurarMensajes() {

        binding.btnToast.setOnClickListener {

            Toast.makeText(
                requireContext(),
                "Este es un mensaje breve",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.btnSnackbar.setOnClickListener {

            Snackbar.make(
                binding.root,
                "Elemento eliminado",
                Snackbar.LENGTH_LONG
            )
                .setAction("Deshacer") {

                    Toast.makeText(
                        requireContext(),
                        "Acción deshecha",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .show()
        }
    }

    private fun configurarDialogo() {

        binding.btnDialogo.setOnClickListener {

            MaterialAlertDialogBuilder(
                requireContext()
            )
                .setTitle(
                    "¿Confirmar acción?"
                )
                .setMessage(
                    "Esta acción no se puede deshacer. ¿Deseas continuar?"
                )
                .setPositiveButton(
                    "Confirmar"
                ) { _, _ ->

                    binding.tvDialogoResultado.text =
                        "Resultado: confirmaste la acción"
                }
                .setNegativeButton(
                    "Cancelar"
                ) { _, _ ->

                    binding.tvDialogoResultado.text =
                        "Resultado: cancelaste la acción"
                }
                .show()
        }
    }

    private fun configurarBottomSheet() {

        binding.btnBottomSheet.setOnClickListener {

            val dialogo =
                BottomSheetDialog(
                    requireContext()
                )

            val contenido =
                LinearLayout(
                    requireContext()
                ).apply {

                    orientation =
                        LinearLayout.VERTICAL

                    setPadding(
                        dp(24),
                        dp(24),
                        dp(24),
                        dp(32)
                    )
                }

            val titulo =
                TextView(
                    requireContext()
                ).apply {

                    text =
                        "Opciones"

                    setTextSize(
                        TypedValue.COMPLEX_UNIT_SP,
                        20f
                    )

                    setTypeface(
                        null,
                        Typeface.BOLD
                    )
                }

            val descripcion =
                TextView(
                    requireContext()
                ).apply {

                    text =
                        "Este panel aparece desde abajo y permite mostrar opciones adicionales sin abandonar la pantalla actual."

                    setTextSize(
                        TypedValue.COMPLEX_UNIT_SP,
                        16f
                    )

                    setPadding(
                        0,
                        dp(12),
                        0,
                        dp(8)
                    )
                }

            contenido.addView(
                titulo
            )

            contenido.addView(
                descripcion
            )

            dialogo.setContentView(
                contenido
            )

            dialogo.show()
        }
    }

    private fun configurarBadge() {

        actualizarBadge()

        binding.btnAgregarNotificacion.setOnClickListener {

            notificaciones++

            actualizarBadge()
        }

        binding.btnLeerNotificacion.setOnClickListener {

            if (notificaciones > 0) {
                notificaciones--
            }

            actualizarBadge()
        }
    }

    private fun actualizarBadge() {

        binding.tvBadge.isVisible =
            notificaciones > 0

        binding.tvBadge.text =
            notificaciones.toString()
    }

    private fun dp(valor: Int): Int {

        return (
                valor *
                        resources.displayMetrics.density
                ).toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}