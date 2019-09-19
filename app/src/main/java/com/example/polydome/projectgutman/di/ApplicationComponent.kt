package com.example.polydome.projectgutman.di

import dagger.Component

@Component
interface ApplicationComponent {
    fun newPresentationComponent(): PresentationComponent
}