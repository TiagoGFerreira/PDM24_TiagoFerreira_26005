package com.example.calculator.Models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.math.sqrt

object CalculatorBrain {

    var displayValue by mutableStateOf("0")
    var currentValue by mutableStateOf("")
    private var operation by mutableStateOf("")
    private var isNewValue by mutableStateOf(false)
    private var isNewList by mutableStateOf(false)
    private var values by mutableStateOf(emptyList<String>())
    private var operations by mutableStateOf(emptyList<String>())
    private val mutableValues = values.toMutableList()
    private val mutableOperations = operations.toMutableList()
    private var memoryValue by mutableDoubleStateOf(0.0)

    fun onButtonClick(value: String) {
        if (value == "." && currentValue.contains(".")) {
            return
        }
        if (displayValue == "0" && value != ".") {
            displayValue = value
            currentValue = value
        } else {
            if (isNewValue && value != ".") {
                displayValue = value
                currentValue += value
                isNewValue = false
            } else {
                displayValue += value
                currentValue += value
            }
        }
    }

    fun clear() {
        displayValue = "0"
        currentValue = ""
        operation = ""
    }

    fun clearEntry() {
        displayValue = displayValue.dropLast(1)
        if (displayValue.isEmpty()) {
            displayValue = "0"
        }
        currentValue = displayValue
    }

    fun applyOperation(op: String) {
        if (currentValue.isNotEmpty()) {
            if (isNewList) {
                mutableValues.clear()
                mutableOperations.clear()
                isNewList = false
            }

            if (operation.isNotEmpty()) {
                displayValue = "0"
                currentValue = ""
            }

            mutableOperations.add(op)
            mutableValues.add(currentValue)
            values = mutableValues
            operations = mutableOperations
            currentValue = ""
            isNewValue = true
        }
    }

    fun calculatePercentage() {
        if (displayValue.isNotEmpty()) {
            val result = displayValue.toDouble() / 100
            displayValue = result.toString()
        }
    }

    fun calculateSquareRoot() {
        if (displayValue.isNotEmpty()) {
            val result = sqrt(displayValue.toDouble())
            displayValue = result.toString()
        }
    }

    fun calculateResult() {
        if (mutableValues.isEmpty() || mutableOperations.isEmpty()) return

        mutableValues.add(currentValue)
        var result = mutableValues[0].toDouble()

        for (i in mutableOperations.indices) {
            val op = mutableOperations[i]
            if (i + 1 < mutableValues.size) {
                val nextValue = mutableValues[i + 1].toDouble()
                result = when (op) {
                    "+" -> result + nextValue
                    "-" -> result - nextValue
                    "x" -> result * nextValue
                    "÷" -> result / nextValue
                    else -> result
                }
            }
        }

        displayValue = if (result % 1 == 0.0) {
            result.toInt().toString()
        } else {
            result.toString()
        }
        currentValue = result.toString()
        isNewList = true
    }

    fun addToMemory() {
        memoryValue += displayValue.toDouble()
    }

    fun subtractFromMemory() {
        memoryValue -= displayValue.toDouble()
    }

    fun recallMemory() {
        if (memoryValue.toInt() != 0) {
            val result = memoryValue

            displayValue = if (result % 1 == 0.0) {
                result.toInt().toString()
            } else {
                result.toString()
            }
        } else {
            displayValue = "0"
        }
    }
}
