package com.example.polydome.projectgutman.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Named("ApplicationContext")
    fun provideApplicationContext(): Context = application

}