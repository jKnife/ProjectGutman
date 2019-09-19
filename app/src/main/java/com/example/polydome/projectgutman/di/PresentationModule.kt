package com.example.polydome.projectgutman.di

import android.content.Context
import com.example.polydome.projectgutman.presentation.ui.common.BaseActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class PresentationModule(private val activity: BaseActivity) {

    @Provides
    @Named("ActivityContext")
    fun provideActivityContext(): Context = activity

}