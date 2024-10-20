package com.example.calculator.Models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.math.sqrt

object CalculatorBrain {

    var displayValue by mutableStateOf("0")
    var currentValue by mutableStateOf("")
    var operation by mutableStateOf("")
    var isNewValue by mutableStateOf(false)
    var isNewList by mutableStateOf(false)
    var values by mutableStateOf(emptyList<String>())
    var operations by mutableStateOf(emptyList<String>())
    val mutableValues = values.toMutableList()
    val mutableOperations = operations.toMutableList()


    fun onButtonClick(value: String) {
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
}
