package com.example.harjoitus1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.harjoitus1.ui.theme.Harjoitus1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Harjoitus1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Tavoitesyke(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Tavoitesyke(modifier: Modifier = Modifier) {
    //käyttäjän kitjoittama ikä
    var ikaTesti by remember { mutableStateOf("") }


    //sykkeen laskenta logiikka
    val ika = ikaTesti.toIntOrNull() ?: 0
    val maksimi = if (ika > 0) 220 - ika else 0
    val alaraja = maksimi * 0.65
    val ylaraja = maksimi * 0.85

    //käyttöliittymä
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Tavoitesyke",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        OutlinedTextField(
            value = ikaTesti,
            onValueChange = {ikaTesti = it },
            label = {Text("Ikä")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Sykerajat: ${"%.0f".format(alaraja)} - ${"%.0f".format(ylaraja)}")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Harjoitus1Theme {
        Tavoitesyke()
    }
}