package com.example.proyect_room_fernando.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
// Asume que UserEntity y UserDao están en el mismo paquete o importados.
import com.example.proyect_room_fernando.data.local.UserEntity
import com.example.proyect_room_fernando.data.local.UserDao

/**
 * AppDatabase:
 * - Clase principal de la base de datos Room.
 * - Aquí registramos:
 * - Las entidades (tablas).
 * - La versión de la BD.
 * - Los DAOs disponibles.
 * - Room genera la implementación concreta en compilación.
 */
@Database(
    entities = [UserEntity::class], // Puedes agregar más entidades aquí
    version = 1,
    exportSchema = false // false para no guardar esquema.
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Devuelve la implementación del DAO de usuarios.
     * Room proporcionará la implementación real.
     */
    abstract fun userDao(): UserDao
}