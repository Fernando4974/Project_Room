package com.example.proyect_room_fernando.data.repository

import com.example.proyect_room_fernando.data.local.UserDao
import com.example.proyect_room_fernando.data.local.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * UserRepository:
 * - Capa intermedia entre el DAO (acceso a BD) y el ViewModel (lógica de UI).
 * - Permite crear API + caché + validaciones sin romper UI/DAO.
 * - Mantiene una arquitectura limpia.
 */
@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    /**
     * Obtiene todos los usuarios de forma reactiva.
     * - Room emite cada vez que cambia la tabla "users".
     * - Flow es perfecto para integrarse con ViewModel + Compose.
     */
    fun getAllUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()

    /**
     * Inserta un usuario en BD.
     * - suspend: debe llamarse desde una coroutine (ej: viewModelScope).
     * - Por eso no puede llamarse desde la UI directamente.
     */
    suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }
}