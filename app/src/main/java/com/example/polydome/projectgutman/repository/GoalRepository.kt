package com.example.polydome.projectgutman.repository

import com.example.polydome.projectgutman.data.ActionEntity
import com.example.polydome.projectgutman.data.ActionEntityDao
import com.example.polydome.projectgutman.data.TriggerType
import com.example.polydome.projectgutman.domain.model.Goal
import io.reactivex.Maybe
import io.reactivex.Observable
import javax.inject.Inject

class GoalRepository
@Inject constructor(private val actionEntityDao: ActionEntityDao) {

    private fun goalFromActionEntity(actionEntity: ActionEntity): Goal {
        return when (actionEntity.triggerType) {
            TriggerType.COUNT -> Goal.Count(actionEntity.targetValue.toInt())
        }
    }

    fun getByActionId(actionId: Int): Observable<Goal> =
        actionEntityDao.getById(actionId)
            .map(this::goalFromActionEntity)

    fun findByActionId(actionId: Int): Maybe<Goal> =
        actionEntityDao.findById(actionId)
            .map(this::goalFromActionEntity)

}