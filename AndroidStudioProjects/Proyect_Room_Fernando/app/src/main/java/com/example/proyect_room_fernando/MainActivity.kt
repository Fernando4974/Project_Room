package com.example.proyect_room_fernando

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme // <-- Importación corregida
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyect_room_fernando.ui.theme.Proyect_Room_FernandoTheme
import com.example.proyect_room_fernando.ui.theme.user.UserScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyect_Room_FernandoTheme { // Usa el nombre real de tu tema
                // 1. Surface es el contenedor principal, debe ser llamado como una función composable.
                Surface(
                    modifier = Modifier.fillMaxSize(), // El modificador va dentro de Surface()
                    color = MaterialTheme.colorScheme.background // El color va dentro de Surface()
                ) {
                    // 2. Box para centrar el placeholder.
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        // 3. Placeholder temporal
                        UserScreen()
                    }
                }
            }
        }
    }
}

// Puedes mantener un preview para verificar el placeholder
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Proyect_Room_FernandoTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Formulario Room (placeholder)")
        }
    }
}