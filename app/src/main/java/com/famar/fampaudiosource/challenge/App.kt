package com.famar.fampaudiosource.challenge

import android.app.Application
import com.famar.fampaudiosource.challenge.di.apiModule
import com.famar.fampaudiosource.challenge.di.dbModule
import com.famar.fampaudiosource.challenge.di.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(dbModule, apiModule, userModule))
        }
    }
}