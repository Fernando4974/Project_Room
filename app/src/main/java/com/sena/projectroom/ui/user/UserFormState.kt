package com.sena.projectroom.ui.user

data class UserFormState(
    val name: String = "",
    val age: String ="",
    val email: String = "",
    val isSaving: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)