package com.example.proyect_room_fernando.ui.theme.user


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.hilt.navigation.compose.hiltViewModel // Importar para usar hiltViewModel()

// Importaciones de tus clases de dominio (ajusta el paquete si es necesario)

 // Asume que la entidad está en el paquete principal
import com.example.proyect_room_fernando.data.local.UserEntity

/**
 * UserScreen:
 * - Es la pantalla principal de la aplicación.
 * - Obtiene el ViewModel usando hiltViewModel().
 * - Observa el estado del formulario y la lista de usuarios.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    // hiltViewModel() es la forma estándar de obtener ViewModels inyectados por Hilt en Compose
    userViewModel: UserViewModel = hiltViewModel()
) {
    // Observamos el estado del formulario y la lista de usuarios
    val uiState by userViewModel.formState.collectAsState()
    val allUsers by userViewModel.users.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Gestión de Usuarios (Room + Hilt)") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- 1. FORMULARIO DE INSERCIÓN ---
            UserForm(uiState = uiState, userViewModel = userViewModel)

            Spacer(modifier = Modifier.height(24.dp))

            // --- 2. LISTA DE USUARIOS ---
            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Usuarios Registrados (${allUsers.size})",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))
            UserList(allUsers = allUsers)
        }
    }
}

// --- Componente: UserForm ---

@Composable
fun UserForm(uiState: UserFormState, userViewModel: UserViewModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nombre (TextField 1)
            OutlinedTextField(
                value = uiState.name,
                onValueChange = userViewModel::onNameChange,
                label = { Text("Nombre") },
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Edad (TextField 2)
            OutlinedTextField(
                value = uiState.age,
                onValueChange = userViewModel::onAgeChange,
                label = { Text("Edad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Email (TextField 3)
            OutlinedTextField(
                value = uiState.email,
                onValueChange = userViewModel::onEmailChange,
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Botón de Guardar
            Button(
                onClick = userViewModel::saveUser,
                enabled = !uiState.isSaving,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (uiState.isSaving) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Guardar Usuario")
                }
            }

            // Mensajes de estado (Error / Éxito)
            uiState.errorMessage?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
            uiState.successMessage?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(it, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

// --- Componente: UserList ---

@Composable
fun UserList(allUsers: List<UserEntity>) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(allUsers, key = { it.id }) { user ->
            UserListItem(user = user)
        }
    }
}

// --- Componente: UserListItem ---

@Composable
fun UserListItem(user: UserEntity) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ID
            Text(
                text = user.id.toString(),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.width(30.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Información del usuario
            Column {
                Text(text = user.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "${user.email} | ${user.age} años",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}