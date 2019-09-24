package com.example.polydome.projectgutman.repository

import com.example.polydome.projectgutman.data.GoalTriggerDao
import com.example.polydome.projectgutman.data.GoalTriggerEntity
import com.example.polydome.projectgutman.data.TriggerType
import com.example.polydome.projectgutman.domain.model.GoalTrigger
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class GoalTriggerRepository
@Inject constructor(private val goalTriggerDao: GoalTriggerDao) {

    fun insert(actionId: Int, goalTrigger: GoalTrigger): Completable = Completable.create {
        val value: Long = when (goalTrigger) {
            is GoalTrigger.Count -> goalTrigger.count.toLong()
        }

        val triggerType = when (goalTrigger) {
            is GoalTrigger.Count -> TriggerType.COUNT
        }

        val entity = GoalTriggerEntity(
            id = null,
            value = value,
            triggerType = triggerType,
            actionId = actionId
        )

        goalTriggerDao.insertTrigger(entity)
    }

    fun getByActionId(actionId: Int): Observable<List<GoalTrigger>> =
        goalTriggerDao.getByActionId(actionId)
            .map { it.map { entity ->
                    when (entity.triggerType) {
                        TriggerType.COUNT -> GoalTrigger.Count(entity.value.toInt())
                    }
                }
            }

}