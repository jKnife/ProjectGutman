package com.example.polydome.projectgutman.repository

import com.example.polydome.projectgutman.data.ActionEntity
import com.example.polydome.projectgutman.data.ActionEntityDao
import com.example.polydome.projectgutman.data.TriggerType
import com.example.polydome.projectgutman.domain.model.Action
import com.example.polydome.projectgutman.domain.model.Goal
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

class ActionRepository
@Inject constructor(private val actionEntityDao: ActionEntityDao) {

    fun insertAction(name: String, goal: Goal): Completable = Completable.defer {
        val triggerType = when (goal) {
            is Goal.Count -> TriggerType.COUNT
        }

        val targetValue: Long = when (goal) {
            is Goal.Count -> goal.targetCount.toLong()
        }

        val actionEntity = ActionEntity(
            id = 0,
            name = name,
            triggerType = triggerType,
            currentValue = 0,
            targetValue = targetValue
        )

        actionEntityDao.insertAction(actionEntity)
    }

    fun findAction(actionId: Int): Maybe<Action> = Maybe.empty()

}