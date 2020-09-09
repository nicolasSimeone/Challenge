package com.famar.fampaudiosource.challenge.di

import com.famar.fampaudiosource.challenge.repositories.UserRepository
import com.famar.fampaudiosource.challenge.repositories.local.db.AppDatabase
import com.famar.fampaudiosource.challenge.repositories.remote.ApiClient
import com.famar.fampaudiosource.challenge.repositories.remote.ApiInstance
import com.famar.fampaudiosource.challenge.repositories.remote.UserService
import com.famar.fampaudiosource.challenge.viewModel.DetailViewModel
import com.famar.fampaudiosource.challenge.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { ApiInstance.create() }
}

val dbModule = module {
    single{ AppDatabase.getDatabase(get()) }
    single{ (get<AppDatabase>().userSavedDAO())}
}

val userModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    single { UserRepository(get(),get()) }
    single { UserService((get())) }
}

