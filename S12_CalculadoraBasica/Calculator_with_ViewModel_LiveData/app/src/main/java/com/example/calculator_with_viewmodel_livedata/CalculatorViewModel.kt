package com.example.calculator_with_viewmodel_livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class CalculatorViewModel : ViewModel() {

    private val _display = MutableLiveData<String>()
    val display: LiveData<String> = _display

    private val _operation = MutableLiveData<String>()
    val operation: LiveData<String> = _operation

    private var currentNumber = ""
    private var previousNumber = ""
    private var currentOperator = ""
    private var isNewOperation = false

    init {
        _display.value = "0"
        _operation.value = ""
    }

    fun onNumberClick(number: String) {
        if (isNewOperation) {
            currentNumber = ""
            isNewOperation = false
        }

        if (currentNumber == "0" && number != ".") {
            currentNumber = number
        } else {
            currentNumber += number
        }

        _display.value = currentNumber
    }

    fun onOperatorClick(operator: String) {
        if (currentNumber.isNotEmpty() && previousNumber.isNotEmpty() && currentOperator.isNotEmpty()) {
            calculateResult()
        }

        if (currentNumber.isNotEmpty()) {
            previousNumber = currentNumber
            currentNumber = ""
        }

        currentOperator = operator
        updateOperationDisplay()
    }

    fun onEqualsClick() {
        if (currentNumber.isNotEmpty() && previousNumber.isNotEmpty() && currentOperator.isNotEmpty()) {
            calculateResult()
            currentOperator = ""
            previousNumber = ""
            _operation.value = ""
            isNewOperation = true
        }
    }

    fun onClearClick() {
        currentNumber = ""
        previousNumber = ""
        currentOperator = ""
        _display.value = "0"
        _operation.value = ""
        isNewOperation = false
    }

    fun onDeleteClick() {
        if (currentNumber.isNotEmpty()) {
            currentNumber = currentNumber.dropLast(1)
            _display.value = if (currentNumber.isEmpty()) "0" else currentNumber
        }
    }

    fun onDecimalClick() {
        if (isNewOperation) {
            currentNumber = "0"
            isNewOperation = false
        }

        if (currentNumber.isEmpty()) {
            currentNumber = "0"
        }

        if (!currentNumber.contains(".")) {
            currentNumber += "."
            _display.value = currentNumber
        }
    }

    private fun calculateResult() {
        try {
            val num1 = BigDecimal(previousNumber)
            val num2 = BigDecimal(currentNumber)

            val result = when (currentOperator) {
                "+" -> num1.add(num2)
                "-" -> num1.subtract(num2)
                "×" -> num1.multiply(num2)
                "÷" -> {
                    if (num2.compareTo(BigDecimal.ZERO) == 0) {
                        _display.value = "Error"
                        return
                    }
                    num1.divide(num2, 10, RoundingMode.HALF_UP)
                }
                else -> BigDecimal.ZERO
            }

            // Formatear el resultado
            val formattedResult = formatResult(result)
            currentNumber = formattedResult
            _display.value = formattedResult

        } catch (e: Exception) {
            _display.value = "Error"
        }
    }

    private fun formatResult(result: BigDecimal): String {
        val df = DecimalFormat("#.##########")
        return df.format(result)
    }

    private fun updateOperationDisplay() {
        _operation.value = "$previousNumber $currentOperator"
    }
}