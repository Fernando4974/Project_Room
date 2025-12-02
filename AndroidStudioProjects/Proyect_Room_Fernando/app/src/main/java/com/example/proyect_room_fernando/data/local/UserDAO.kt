package com.example.proyect_room_fernando.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.proyect_room_fernando.data.local.UserDao // Importar la entidad

/**
 * UserDao:
 * - Es la interfaz que define cómo accedemos a la tabla "users".
 * - Aquí NO hay lógica de negocio, solo "qué consultas" se pueden hacer.
 * - Room genera la implementación real en tiempo de compilación.
 */
@Dao
interface UserDao {

    /**
     * insertUser:
     * - Inserta un usuario en la tabla.
     * - suspend: se ejecuta en coroutine (no bloquea el hilo principal).
     * - onConflict = REPLACE:
     * Si hay conflicto de ID, reemplaza el registro.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    /**
     * getAllUsers:
     * - Devuelve un Flow (flujo reactivo) con la lista de usuarios.
     * - Cada vez que cambia la tabla "users", Room emite una nueva lista.
     * - Perfecto para Compose + ViewModel:
     * podemos colectar este Flow y pintar la UI reactiva.
     */
    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAllUsers(): Flow<List<UserEntity>>
}