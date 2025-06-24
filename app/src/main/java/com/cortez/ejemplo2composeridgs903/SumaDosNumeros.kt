package com.cortez.ejemplo2composeridgs903

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SumaDosNumeros()
{
    var num1 by remember {mutableStateOf("")}
    var num2 by remember {mutableStateOf("")}
    var resultado by remember {mutableStateOf("")}
    var selectedOption by remember { mutableStateOf(Operation.SUMA) }
    val options = listOf(Operation.SUMA, Operation.RESTA, Operation.MULTIPLICACION, Operation.DIVISION)

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
        TextField(
            value = num1,
            onValueChange = {num1 = it},
            label= {Text("Número 1")},
            keyboardOptions = KeyboardOptions(keyboardType=KeyboardType.Number)
        )
        TextField(
            value = num2,
            onValueChange = {num2 = it},
            label= {Text("Numero 2")},
            keyboardOptions = KeyboardOptions(keyboardType=KeyboardType.Number)
        )

        options.forEach { operation ->
            Row(
                Modifier
                    .selectable(
                        selected = (operation == selectedOption),
                        onClick = { selectedOption = operation }
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (operation == selectedOption),
                    onClick = { selectedOption = operation }
                )
                Text(
                    text = operation.name,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Button(onClick = {
            val n1 = num1.toIntOrNull()
            val n2 = num2.toIntOrNull()

            resultado= if (n1 != null && n2 != null){
                when (selectedOption) {
                    Operation.SUMA -> "Resultado: ${n1 + n2}"
                    Operation.RESTA -> "Resultado: ${n1 - n2}"
                    Operation.MULTIPLICACION -> "Resultado: ${n1 * n2}"
                    Operation.DIVISION -> {
                        if (n2 != 0) {
                            "Resultado: ${n1.toDouble() / n2.toDouble()}"
                        } else {
                            "Error: División por cero"
                        }
                    }
                }
            }else{
                "Por favor, ingrese números válidos."
            }
        }){
            Text("Calcular")
        }
        Text(text = resultado)
    }
}

enum class Operation { SUMA, RESTA, MULTIPLICACION, DIVISION }

