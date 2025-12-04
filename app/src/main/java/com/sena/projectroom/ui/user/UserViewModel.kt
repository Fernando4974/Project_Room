package com.sena.projectroom.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sena.projectroom.data.local.UserEntity
import com.sena.projectroom.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){
    private val _formState = MutableStateFlow(UserFormState())
    val formState: StateFlow<UserFormState> = _formState.asStateFlow()

    val users: StateFlow<List<UserEntity>> =
        userRepository.getAllUsers()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun onNameChange(newName: String) {
        _formState.value = _formState.value.copy(
            name = newName,
            errorMessage = null,
            successMessage = null
        )
    }

    fun onAgeChange(newAge: String) {
        _formState.value = _formState.value.copy(
            age = newAge,
            errorMessage = null,
            successMessage = null
        )
    }


    fun onEmailChange(newEmail: String) {
        _formState.value = _formState.value.copy(
            email = newEmail,
            errorMessage = null,
            successMessage = null
        )
    }

    fun saveUser() {
        val currentState = _formState.value
        if (currentState.name.isBlank() ||
            currentState.age.isBlank() ||
            currentState.email.isBlank()
        ) {
            _formState.value = currentState.copy(
                errorMessage = "Todos los campos son obligatorios",
                successMessage = null
            )
            return
        }
        val ageInt = currentState.age.toIntOrNull()
        if (ageInt == null || ageInt <= 0){
            _formState.value=currentState.copy(
                errorMessage = "La edad debe ser un número entero positivo",
                successMessage = null
            )
            return
        }
        if (!currentState.email.contains("@")){
            _formState.value=currentState.copy(
                errorMessage = "El email no es válido",
                successMessage = null
            )
            return
        }
        viewModelScope.launch {
            try {
                _formState.value =currentState.copy(
                    isSaving = true,
                    errorMessage = null,
                    successMessage = null
                )
                val user = UserEntity(
                    name = currentState.name,
                    age = ageInt,
                    email = currentState.email
                )
                userRepository.insertUser(user)
                _formState.value = UserFormState(
                    successMessage = "Usuario guardado correctamente",
                )
            }catch (e: Exception){
                _formState.value = currentState.copy(
                    isSaving = false,
                    errorMessage = "Error al guardar el usuario",
                    successMessage = null
                )
            }finally {
                _formState.value =_formState.value.copy(
                    isSaving = false
                )
            }
        }
    }
}


