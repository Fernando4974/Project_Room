package com.example.proyect_room_fernando.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * UserEntity:
 * - Representa la tabla "users" dentro de la base de datos Room.
 * - Cada instancia de UserEntity es una fila en la tabla.
 * - Usamos @Entity para decirle a Room que esta data class se mapea a una tabla.
 * - El nombre de la tabla será "users".
 */
@Entity(tableName = "users")
data class UserEntity(
    /**
     * id:
     * - Clave primaria de la tabla.
     * - autoGenerate = true hace que Room asigne el ID automáticamente
     * cuando insertamos un nuevo registro.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    /**
     * name:
     * - Nombre del usuario.
     * - No lo marcamos como nullable porque queremos que siempre exista.
     */
    @ColumnInfo(name = "name")
    val name: String,

    /**
     * age:
     * - Edad del usuario.
     * - Int simple; podrías usar nullable si quieres permitir "sin edad".
     */
    @ColumnInfo(name = "age")
    val age: Int,

    /**
     * email:
     * - Correo electrónico del usuario.
     * - En un escenario real podrías agregar validaciones antes de guardar.
     */
    @ColumnInfo(name = "email")
    val email: String
)