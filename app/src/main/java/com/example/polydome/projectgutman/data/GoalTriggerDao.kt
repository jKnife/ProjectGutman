package com.example.polydome.projectgutman.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GoalTriggerDao {

    @Query("SELECT * from goalTrigger")
    fun getAllTriggers(): List<GoalTriggerEntity>

    @Insert
    fun insertTrigger(goalTriggerEntity: GoalTriggerEntity)

    @Query("DELETE from goalTrigger")
    fun deleteAllTriggers()

    @Delete
    fun deleteOneTrigger(goalTriggerEntity: GoalTriggerEntity)

}