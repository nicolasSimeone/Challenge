package com.famar.fampaudiosource.challenge.repositories.remote

import com.famar.fampaudiosource.challenge.model.User
import com.famar.fampaudiosource.challenge.model.results
import com.famar.fampaudiosource.challenge.utils.Result

class UserService(private val api: ApiClient) {

    suspend fun getUsers(): Result<results> {

        val response = api.getUserList().await()
        val body = response.body()
        body?.let {
            return Result.Success(body)
        } ?: run {
            return Result.Error(
                Exception("error")
            )
        }
    }
}