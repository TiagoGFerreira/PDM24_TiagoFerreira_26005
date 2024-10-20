package com.example.calculator.ui

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text

@Composable
fun CalculatorButton(
    text: String,
    onClick: () -> Unit,
    backGroundColors: ButtonColors = ButtonDefaults.buttonColors(),
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = backGroundColors,
    ) {
        Text(text = text, fontSize = 22.sp)
    }
}
