package com.example.polydome.projectgutman.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(value = [Converters::class])
@Database(entities = [ActionEntity::class, GoalTriggerEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun actionEntityDao(): ActionEntityDao
    abstract fun goalTriggerDao(): GoalTriggerDao
}