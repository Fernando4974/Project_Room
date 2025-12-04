package com.sena.projectroom.data.repository

import com.sena.projectroom.data.local.UserDao
import com.sena.projectroom.data.local.UserEntity
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    fun getAllUsers(): Flow<List<UserEntity>> =
        userDao.getAllUsers()

    suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }
}
