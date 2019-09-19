package com.example.polydome.projectgutman.di

import com.example.polydome.projectgutman.data.ActionEntityDao
import com.example.polydome.projectgutman.presentation.viewmodel.ActionViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideActionViewModel(actionEntityDao: ActionEntityDao): ActionViewModel {
        return ActionViewModel(actionEntityDao)
    }

}