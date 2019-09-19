package com.example.polydome.projectgutman.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DatabaseModule::class])
interface ApplicationComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}