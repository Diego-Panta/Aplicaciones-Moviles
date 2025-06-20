package com.example.san_pedrito_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.san_pedrito_app.R
import com.example.san_pedrito_app.databinding.FragmentFormBinding
import com.example.san_pedrito_app.utils.Validator
import com.example.san_pedrito_app.viewmodel.SanPedritoViewModel
import com.example.san_pedrito_app.viewmodel.SanPedritoViewModelFactory
import com.google.android.material.snackbar.Snackbar

class FormFragment : Fragment() {
    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SanPedritoViewModel by viewModels { SanPedritoViewModelFactory() }
    private val tipos = listOf("Docente", "Alumno")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpinners()
        setupForm()

    }

    private fun setupSpinners() {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            tipos
        )
        binding.spinnerTipo.setAdapter(adapter)
    }

    private fun setupForm() {
        binding.btnRegistrar.setOnClickListener {
            if (validarFormulario()) {
                registrarParticipante()
            }
        }

        // Manejar el botón de retroceso
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // Personalizar comportamiento si es necesario
            findNavController().navigateUp()
        }
    }


    private fun validarFormulario(): Boolean {
        val nombres = binding.etNombres.text.toString().trim()
        val apellidos = binding.etApellidos.text.toString().trim()
        val codigo = binding.etCodigo.text.toString().trim()
        val dni = binding.etDni.text.toString().trim()
        val correo = binding.etCorreo.text.toString().trim()
        val telefono = binding.etTelefono.text.toString().trim()
        val tipo = binding.spinnerTipo.text.toString().trim()
        val tipoPos = tipos.indexOf(tipo)

        var valido = true

        if (!Validator.validarNombre(nombres)) {
            binding.etNombres.error = "Ingrese nombres válidos"
            valido = false
        }

        if (!Validator.validarNombre(apellidos)) {
            binding.etApellidos.error = "Ingrese apellidos válidos"
            valido = false
        }

        if (!Validator.validarCodigo(codigo)) {
            binding.etCodigo.error = "Ingrese un código válido"
            valido = false
        }

        if (!Validator.validarDNI(dni)) {
            binding.etDni.error = "Ingrese un DNI válido (8 dígitos)"
            valido = false
        }

        if (!Validator.validarCorreo(correo)) {
            binding.etCorreo.error = "Ingrese un correo válido"
            valido = false
        }

        if (!Validator.validarTelefono(telefono)) {
            binding.etTelefono.error = "Ingrese un teléfono válido (9 dígitos)"
            valido = false
        }

        if (tipo.isEmpty() || tipoPos == -1) {
            Snackbar.make(binding.root, "Seleccione un tipo", Snackbar.LENGTH_SHORT).show()
            valido = false
        }

        return valido
    }

    private fun registrarParticipante() {
        val nombres = binding.etNombres.text.toString().trim()
        val apellidos = binding.etApellidos.text.toString().trim()
        val codigo = binding.etCodigo.text.toString().trim()
        val dni = binding.etDni.text.toString().trim()
        val correo = binding.etCorreo.text.toString().trim()
        val telefono = binding.etTelefono.text.toString().trim()
        val tipo = binding.spinnerTipo.text.toString().trim()

        val registrado = viewModel.registrarParticipante(
            nombres,
            apellidos,
            codigo,
            dni,
            correo,
            telefono,
            tipo
        )

        if (registrado) {
            Snackbar.make(
                binding.root,
                "Registro exitoso. ¡Nos vemos en el desfile!",
                Snackbar.LENGTH_LONG
            ).show()
            limpiarFormulario()
        } else {
            Snackbar.make(
                binding.root,
                "Error al registrar. Intente nuevamente.",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    fun limpiarFormulario() {
        binding.etNombres.text?.clear()
        binding.etApellidos.text?.clear()
        binding.etCodigo.text?.clear()
        binding.etDni.text?.clear()
        binding.etCorreo.text?.clear()
        binding.etTelefono.text?.clear()
        binding.spinnerTipo.setText("") // Limpia el campo
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}