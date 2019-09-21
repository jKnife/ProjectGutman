package com.example.polydome.projectgutman.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable

@Dao
interface GoalTriggerDao {

    @Query("SELECT * from goalTrigger")
    fun getAllTriggers(): Observable<List<GoalTriggerEntity>>

    @Insert
    fun insertTrigger(goalTriggerEntity: GoalTriggerEntity)

    @Query("DELETE from goalTrigger")
    fun deleteAllTriggers()

    @Delete
    fun deleteOneTrigger(goalTriggerEntity: GoalTriggerEntity)

    @Query("SELECT * FROM goalTrigger WHERE actionId = :actionId")
    fun getByActionId(actionId: Int): Observable<List<GoalTriggerEntity>>

}