package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.sqrt
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Calculator()
            }
        }
    }
}

@Composable
fun CreateButton(text: String, onClick: () -> Unit, backGroundColors: ButtonColors) {
    Button(onClick = onClick, colors = backGroundColors) {
        Text(text = text)
    }
}

@Composable
fun Calculator() {

    var displayValue by remember { mutableStateOf("0") }
    var currentValue by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }
    var isNewValue by remember { mutableStateOf(false) }
    var values by remember { mutableStateOf(emptyList<String>()) }
    val mutableValues = values.toMutableList()
    var operations by remember { mutableStateOf(emptyList<String>()) }
    val mutableOperations = operations.toMutableList()

    fun onButtonClick(value: String) {
        if (displayValue == "0") {
            displayValue = value
            currentValue = value
        } else {
            if (isNewValue && value != ".")
            {
                displayValue = value
                currentValue += value
                isNewValue = false
            }
            else
            {
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

    fun setOperation(op: String) {
        if (currentValue.isNotEmpty()) {
            mutableOperations.add(op)
            mutableValues.add(currentValue)
            values = mutableValues.toList()
            operations = mutableOperations.toList()
            currentValue = ""
            isNewValue = true

        }
    }

    fun calculatePercentage(){
        if(displayValue.isNotEmpty())
        {
            val result = displayValue.toDouble() /  100
            displayValue = result.toString()
        }
    }

    fun calculateSquareRoot(){
        if(displayValue.isNotEmpty())
        {
            val result = sqrt(displayValue.toDouble())
            displayValue = result.toString()
        }
    }

    fun calculateResult() {
        if (mutableValues.isEmpty() || mutableOperations.isEmpty()) return


        mutableValues.add(currentValue)
        var result = mutableValues[0].toDouble()

        for (i in 0 until mutableOperations.size) {
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
        mutableValues.clear()
        mutableOperations.clear()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = displayValue,
            modifier = Modifier
                 .wrapContentWidth(Alignment.End)
                .padding(1.dp),
            fontSize = 32.sp,
            textAlign = TextAlign.Right
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CreateButton("MRC", onClick = { },
                backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
            CreateButton("M-", onClick = { },
                backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
            CreateButton("M+", onClick = { },
                backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
            CreateButton("C", onClick = { clear() },
                backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White))
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CreateButton("√", onClick = { calculateSquareRoot() },
                backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
            CreateButton("%", onClick = { calculatePercentage() },
                backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
            CreateButton("±", onClick = { if(!currentValue.contains("-")) onButtonClick("-") },
                backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
            CreateButton("CE", onClick = { clearEntry() },
                backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White))
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CreateButton("7", onClick = { onButtonClick("7") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White))
                CreateButton("4", onClick = { onButtonClick("4") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White))
                CreateButton("1", onClick = { onButtonClick("1") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White))
                CreateButton("0", onClick = { onButtonClick("0") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White))
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CreateButton("8", onClick = { onButtonClick("8") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White))
                CreateButton("5", onClick = { onButtonClick("5") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White))
                CreateButton("2", onClick = { onButtonClick("2") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White))
                CreateButton(".", onClick = { onButtonClick(".") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White))
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CreateButton("9", onClick = { onButtonClick("9") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White))
                CreateButton("6", onClick = { onButtonClick("6") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White))
                CreateButton("3", onClick = { onButtonClick("3") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White))
                CreateButton("=", onClick = { calculateResult() },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White))
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CreateButton("÷", onClick = { setOperation("÷") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
                CreateButton("x", onClick = { setOperation("x") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
                CreateButton("-", onClick = { setOperation("-") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
                CreateButton("+", onClick = { setOperation("+") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calculator()
}