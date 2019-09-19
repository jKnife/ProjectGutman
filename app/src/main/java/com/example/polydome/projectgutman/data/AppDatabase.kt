package com.example.polydome.projectgutman.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ActionEntity::class, GoalTriggerEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun actionEntityDao(): ActionEntityDao
    abstract fun goalTriggerDao(): GoalTriggerDao
}