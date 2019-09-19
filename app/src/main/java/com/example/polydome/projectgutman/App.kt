package com.example.polydome.projectgutman

import android.app.Application
import com.example.polydome.projectgutman.di.ApplicationComponent
import com.example.polydome.projectgutman.di.ApplicationModule
import com.example.polydome.projectgutman.di.DaggerApplicationComponent

class App : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}