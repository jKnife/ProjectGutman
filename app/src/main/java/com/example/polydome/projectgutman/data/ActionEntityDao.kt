package com.example.polydome.projectgutman.data

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface ActionEntityDao {

    @Query("SELECT * from actionEntity")
    fun getAllActions(): List<ActionEntity>

    @Insert
    fun insertAction(actionEntity: ActionEntity): Completable

    @Query("DELETE from actionEntity")
    fun deleteAllActions()

    @Delete
    fun deleteOneAction(actionEntity: ActionEntity)

    @Query("SELECT id FROM actionEntity")
    fun getIds(): Observable<List<Int>>

    @Query("SELECT * FROM actionEntity where id = :id")
    fun getById(id: Int): Observable<ActionEntity>

    @Update
    fun updateAction(actionEntity: ActionEntity): Completable

}