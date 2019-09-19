package com.example.polydome.projectgutman.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ActionEntityDao {

    @Query("SELECT * from actionEntity")
    fun getAllActions(): List<ActionEntity>

    @Insert
    fun insertAction(actionEntity: ActionEntity)

    @Query("DELETE from actionEntity")
    fun deleteAllActions()

    @Delete
    fun deleteOneAction(actionEntity: ActionEntity)
}