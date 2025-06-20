package com.example.san_pedrito_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.san_pedrito_app.R
import com.example.san_pedrito_app.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar la información principal
        val infoText = """
            El Desfile San Pedrito es un evento tradicional que celebra la festividad 
            del patrón de los ingenieros, San Pedro. Participan docentes y alumnos 
            de la Facultad de Ingeniería de Sistemas en un colorido desfile por las 
            principales calles de la ciudad.
            
            Este año el evento se realizará el día 26 de junio a partir de las 9:00 AM
            en la Plaza de Armas de Moquegua.
        """.trimIndent()

        binding.textInfo.text = infoText

        // Configurar información de contacto (si existe en el layout)
        if (binding.root.findViewById<TextView>(R.id.textContacto) != null) {
            val contactoText = """
                Para más información contactar con:
                
                Comisión Organizadora
                Email: transparencia@uns.edu.pe
                Teléfono: (51)-43-310445
                Oficina: Pabellón de Sistemas, 2do piso
            """.trimIndent()

            binding.root.findViewById<TextView>(R.id.textContacto).text = contactoText
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}