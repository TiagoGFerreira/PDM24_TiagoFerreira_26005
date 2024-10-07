package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun CreateButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}

@Composable
fun addFunction(a:Int, b:Int): Int {
    val sum = a + b
    return sum
}

@Composable
fun subtractFunction(a:Int, b:Int): Int {
    val sub = a - b
    return sub
}

@Composable
fun multiplyFunction(a:Int, b:Int): Int {
    val mul = a * b
    return mul
}

@Composable
fun divideFunction(a:Int, b:Int): Double {
    val mul = a / b
    return mul.toDouble()
}

@Composable
fun Calculator(){
    Row(verticalAlignment = Alignment.CenterVertically)
    {
        Column {
            CreateButton("7"){}
            CreateButton("4"){}
            CreateButton("3"){}
            CreateButton("0"){}
        }


        Column {
            CreateButton("8"){}
            CreateButton("5"){}
            CreateButton("2"){}
            CreateButton("-"){}

        }


        Column {
            CreateButton("9"){}
            CreateButton("6"){}
            CreateButton("1"){}
            CreateButton("="){}
        }
        Column {
            CreateButton("÷"){}
            CreateButton("x"){}
            CreateButton("-"){}
            CreateButton("+"){}
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calculator()
}