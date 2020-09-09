package com.famar.fampaudiosource.challenge.repositories

import com.famar.fampaudiosource.challenge.model.User
import com.famar.fampaudiosource.challenge.model.UserSaved
import com.famar.fampaudiosource.challenge.model.results
import com.famar.fampaudiosource.challenge.repositories.local.db.AppDatabase
import com.famar.fampaudiosource.challenge.repositories.remote.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.famar.fampaudiosource.challenge.utils.Result

class UserRepository(private val remoteDataSource: UserService, private val appDatabase: AppDatabase) {

    suspend fun getUsers(): results = withContext(Dispatchers.IO)
    {
        val result = remoteDataSource.getUsers()

        when (result) {
            is Result.Success -> result.data
            is Result.Error -> throw result.exception
        }
    }

    suspend fun getUsersResultsDb(): List<UserSaved> = withContext(Dispatchers.IO) {
        val resultUserDb = appDatabase.userSavedDAO().getAll()

        return@withContext resultUserDb
    }

    suspend fun insertUserSavedDb(email: String?, picture: String?, name: String?, phone:String?) = withContext(Dispatchers.IO) {
        val userSaved = UserSaved(email, picture, name, phone)
        val resultUserSavedDb = appDatabase.userSavedDAO().insertUserSaved(userSaved)

        return@withContext resultUserSavedDb
    }

}