package com.example.polydome.projectgutman.repository

import com.example.polydome.projectgutman.data.ActionEntityDao
import com.example.polydome.projectgutman.data.TriggerType
import com.example.polydome.projectgutman.domain.model.Goal
import io.reactivex.Observable
import javax.inject.Inject

class GoalRepository
@Inject constructor(private val actionEntityDao: ActionEntityDao) {

    fun getByActionId(actionId: Int): Observable<Goal> =
        actionEntityDao.getById(actionId)
            .map {
                when (it.triggerType) {
                    TriggerType.COUNT -> Goal.Count(0, it.targetValue.toInt())
                }
            }

}