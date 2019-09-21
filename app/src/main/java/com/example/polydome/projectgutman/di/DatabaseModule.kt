package com.example.polydome.projectgutman.di

import android.content.Context
import androidx.room.Room
import com.example.polydome.projectgutman.data.ActionEntityDao
import com.example.polydome.projectgutman.data.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@Named("ApplicationContext") applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    fun provideActionEntityDao(appDatabase: AppDatabase): ActionEntityDao {
        return appDatabase.actionEntityDao()
    }

}