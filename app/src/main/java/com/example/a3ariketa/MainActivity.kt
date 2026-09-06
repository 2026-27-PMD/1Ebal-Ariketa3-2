package com.example.a3ariketa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private var loginOK = false

class MainActivity : ComponentActivity() {


    private val launcher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                loginOK = result.data?.getBooleanExtra("loginOK", false) ?: false

                Log.d("APP", "Login OK: $loginOK")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //un callback, es decir, una función que le pasas a otra función para que la ejecute más tarde.
            //hacemos el composable más reutilizable:
            PantallaInicio(launcher)
        }
    }
}

@Composable
fun PantallaInicio(launcher: ActivityResultLauncher<Intent>) {
    val context = LocalContext.current
    var user by rememberSaveable { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Hasierako Pantaila")

        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("Erabiltzailea") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val intent = Intent(context, LoginActivity::class.java)

                intent.putExtra("user", user)

                launcher.launch(intent)
            }
        ) {
            Text("Login")
        }
        Button(
            onClick = {
                if (!loginOK) {
                    user = ""
                }
                val intent = Intent(context, AgurraActivity::class.java)

                intent.putExtra("user", user)

                launcher.launch(intent)
            }
        ) {
            Text("Agurra")
        }
    }
}
