package com.example.polydome.projectgutman.repository

import com.example.polydome.projectgutman.data.GoalTriggerDao
import com.example.polydome.projectgutman.data.TriggerType
import com.example.polydome.projectgutman.domain.model.GoalTrigger
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class GoalTriggerRepository
@Inject constructor(private val goalTriggerDao: GoalTriggerDao) {

    fun insert(goalTrigger: GoalTrigger): Completable = Completable.create {

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