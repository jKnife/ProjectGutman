package com.example.polydome.projectgutman.presentation.ui.common

import androidx.appcompat.app.AppCompatActivity
import com.example.polydome.projectgutman.App
import com.example.polydome.projectgutman.di.PresentationComponent
import com.example.polydome.projectgutman.di.PresentationModule

abstract class BaseActivity: AppCompatActivity() {

    protected val presentationComponent: PresentationComponent by lazy {
        (application as App).applicationComponent.newPresentationComponent(PresentationModule(this))
    }

}