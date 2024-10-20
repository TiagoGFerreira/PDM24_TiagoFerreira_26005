package com.example.calculator.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.Models.CalculatorBrain

@Composable
fun CalculatorBody(brain: CalculatorBrain) {
    Column(
        modifier = Modifier.padding(2.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CalculatorScreen(displayValue = brain.displayValue)

        Column(
            modifier = Modifier.fillMaxHeight().weight(14f)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    text = "MRC",
                    onClick = { },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "M-",
                    onClick = { },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "M+",
                    onClick = { },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "C",
                    onClick = { brain.clear() },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    text = "√",
                    onClick = { brain.calculateSquareRoot() },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "%",
                    onClick = { brain.calculatePercentage() },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "±",
                    onClick = { if (!brain.currentValue.contains("-")) brain.onButtonClick("-") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "CE",
                    onClick = { brain.clearEntry() },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    text = "7",
                    onClick = { brain.onButtonClick("7") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "8",
                    onClick = { brain.onButtonClick("8") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "9",
                    onClick = { brain.onButtonClick("9") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "÷",
                    onClick = { brain.applyOperation("÷") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    text = "4",
                    onClick = { brain.onButtonClick("4") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "5",
                    onClick = { brain.onButtonClick("5") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "6",
                    onClick = { brain.onButtonClick("6") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "x",
                    onClick = { brain.applyOperation("x") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    text = "1",
                    onClick = { brain.onButtonClick("1") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "2",
                    onClick = { brain.onButtonClick("2") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "3",
                    onClick = { brain.onButtonClick("3") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "-",
                    onClick = { brain.applyOperation("-") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    text = "0",
                    onClick = { brain.onButtonClick("0") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = ".",
                    onClick = { brain.onButtonClick(".") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "=",
                    onClick = { brain.calculateResult() },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "+",
                    onClick = { brain.applyOperation("+") },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculatorBody() {
    val brain = CalculatorBrain
    CalculatorBody(brain = brain)
}
