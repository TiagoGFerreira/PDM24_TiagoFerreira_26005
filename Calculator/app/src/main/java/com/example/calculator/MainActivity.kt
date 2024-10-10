package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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

    fun onButtonClick(value: String) {
        if (displayValue == "0") {
            displayValue = value.toString()
        } else {
            displayValue += value
        }
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
            CreateButton("C", onClick = { },
                backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White))
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CreateButton("√", onClick = { },
                backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
            CreateButton("%", onClick = { },
                backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
            CreateButton("±", onClick = { },
                backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
            CreateButton("CE", onClick = { },
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
                CreateButton("=", onClick = { },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White))
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CreateButton("÷", onClick = { },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
                CreateButton("x", onClick = { },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
                CreateButton("-", onClick = { },
                    backGroundColors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White))
                CreateButton("+", onClick = { },
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