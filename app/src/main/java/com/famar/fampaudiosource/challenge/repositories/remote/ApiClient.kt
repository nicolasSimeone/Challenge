package com.famar.fampaudiosource.challenge.repositories.remote

import com.famar.fampaudiosource.challenge.model.User
import com.famar.fampaudiosource.challenge.model.results
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET("/api/?results=150")
    fun getUserList(): Deferred<Response<results>>
}