package com.example.calculator_with_viewmodel_livedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.calculator_with_viewmodel_livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.display.observe(this, Observer { display ->
            binding.tvDisplay.text = display
        })

        viewModel.operation.observe(this, Observer { operation ->
            binding.tvOperation.text = operation
        })
    }

    private fun setupClickListeners() {
        // Números
        binding.btn0.setOnClickListener { viewModel.onNumberClick("0") }
        binding.btn1.setOnClickListener { viewModel.onNumberClick("1") }
        binding.btn2.setOnClickListener { viewModel.onNumberClick("2") }
        binding.btn3.setOnClickListener { viewModel.onNumberClick("3") }
        binding.btn4.setOnClickListener { viewModel.onNumberClick("4") }
        binding.btn5.setOnClickListener { viewModel.onNumberClick("5") }
        binding.btn6.setOnClickListener { viewModel.onNumberClick("6") }
        binding.btn7.setOnClickListener { viewModel.onNumberClick("7") }
        binding.btn8.setOnClickListener { viewModel.onNumberClick("8") }
        binding.btn9.setOnClickListener { viewModel.onNumberClick("9") }

        // Operadores
        binding.btnAdd.setOnClickListener { viewModel.onOperatorClick("+") }
        binding.btnSubtract.setOnClickListener { viewModel.onOperatorClick("-") }
        binding.btnMultiply.setOnClickListener { viewModel.onOperatorClick("×") }
        binding.btnDivide.setOnClickListener { viewModel.onOperatorClick("÷") }

        // Funciones especiales
        binding.btnEquals.setOnClickListener { viewModel.onEqualsClick() }
        binding.btnClear.setOnClickListener { viewModel.onClearClick() }
        binding.btnDelete.setOnClickListener { viewModel.onDeleteClick() }
        binding.btnDecimal.setOnClickListener { viewModel.onDecimalClick() }
    }
}