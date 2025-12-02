package com.example.proyect_room_fernando.di



import android.content.Context
import androidx.room.Room
import com.example.proyect_room_fernando.data.local.AppDatabase
import com.example.proyect_room_fernando.data.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * DatabaseModule:
 * - Se encarga de proveer la instancia Singleton de AppDatabase y UserDao.
 * - Usamos Hilt para que no tengas que crear manualmente la BD.
 */
@Module
@InstallIn(SingletonComponent::class) // Indica que las dependencias durarán lo que dure la aplicación
object DatabaseModule {

    /**
     * Provee una única instancia (Singleton) de AppDatabase para toda la app.
     */
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context // Hilt inyecta el Context de la aplicación
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "room_form_db" // Nombre del archivo de la BD
        )
            // fallbackToDestructiveMigration(): Útil en pruebas. Destruye datos si la versión cambia.
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Provee una instancia de UserDao obtenida desde la BD.
     * Hilt se encargará de inyectarla donde se necesite (ej. en el Repository).
     */
    @Provides
    fun provideUserDao(
        appDatabase: AppDatabase // Hilt inyecta la instancia de AppDatabase que definimos arriba
    ): UserDao = appDatabase.userDao()
}