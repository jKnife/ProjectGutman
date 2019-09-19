package com.example.polydome.projectgutman.di

import com.example.polydome.projectgutman.presentation.ui.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class, ViewModelModule::class])
interface PresentationComponent {
    fun inject(mainActivity: MainActivity)
}