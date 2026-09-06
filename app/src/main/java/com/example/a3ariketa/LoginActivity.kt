package com.example.a3ariketa

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = intent.getStringExtra("user") ?: ""

        setContent {
            MaterialTheme {
                LoginScreen(
                    user = user,
                    onSendResult = { pass ->

                        val data = Intent().apply {
                            putExtra(
                                "loginOK",
                                user == pass
                            )
                        }

                        setResult(
                            RESULT_OK,
                            data
                        )
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun LoginScreen(user: String, onSendResult: (String) -> Unit) {
//    var user by re)member { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "LOGIN",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Erabiltzailea $user",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Blue
        )
//        OutlinedTextField(
//            value = user,
//            onValueChange = { user = it },
//            label = { Text("Erabiltzailea") },
//            modifier = Modifier.fillMaxWidth()
//        )
        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Pashitza") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { onSendResult(pass) }

            ) {
                Text("Sartu")
            }

            Button(onClick = {
                //navController.popBackStack()
            }) {
                Text("Itzuli")
            }
        }
    }
}

