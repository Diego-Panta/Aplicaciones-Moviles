package com.example.san_pedrito_app.view.fragments.com.example.san_pedrito_app.view.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.san_pedrito_app.R
import com.example.san_pedrito_app.databinding.FragmentProgramBinding

class ProgramFragment : Fragment() {
    private var _binding: FragmentProgramBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar la tabla de horarios
        val horarios = listOf(
            "8:00 AM" to "Concentración de participantes",
            "9:00 AM" to "Inicio del desfile",
            "10:00 AM" to "Acto protocolar",
            "11:00 AM" to "Presentación de danzas",
            "12:00 PM" to "Premiación a delegaciones",
            "1:00 PM" to "Almuerzo de confraternidad"
        )

        horarios.forEachIndexed { index, (hora, actividad) ->
            val row = TableRow(requireContext()).apply {
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )

                // Alternar colores de fondo para mejor legibilidad
                setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        if (index % 2 == 0) R.color.white else R.color.bizarre
                    )
                )

                // Padding para las celdas
                setPadding(0, 12.dpToPx(), 0, 12.dpToPx())
            }

            // Celda de hora
            TextView(requireContext()).apply {
                text = hora
                setTextColor(ContextCompat.getColor(requireContext(), R.color.cardinal_dark))
                textSize = 14f
                typeface = Typeface.DEFAULT_BOLD
                gravity = View.TEXT_ALIGNMENT_CENTER
                layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
            }.also { row.addView(it) }

            // Celda de actividad
            TextView(requireContext()).apply {
                text = actividad
                setTextColor(ContextCompat.getColor(requireContext(), R.color.outer_space))
                textSize = 14f
                gravity = View.TEXT_ALIGNMENT_CENTER
                layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2f)
            }.also { row.addView(it) }

            binding.horarioTable.addView(row)
        }



        // Configurar el texto de información importante
        val importanteText = """
            - Todos los participantes deben presentarse puntualmente
            - Vestimenta formal o con el uniforme institucional
            - Portar el carné universitario o documento de identidad
            - Seguir las indicaciones del personal organizador
        """.trimIndent()

        // Si el layout tiene un TextView para la información importante
        if (binding.root.findViewById<TextView>(R.id.textImportante) != null) {
            binding.root.findViewById<TextView>(R.id.textImportante).text = importanteText
        }
    }

    private fun Int.dpToPx(): Int {
        val density = resources.displayMetrics.density
        return (this * density).toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}