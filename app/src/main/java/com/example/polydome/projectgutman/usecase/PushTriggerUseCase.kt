package com.example.polydome.projectgutman.usecase

import android.annotation.SuppressLint
import com.example.polydome.projectgutman.domain.model.GoalState
import com.example.polydome.projectgutman.domain.model.GoalTrigger
import com.example.polydome.projectgutman.repository.ActionRepository
import com.example.polydome.projectgutman.repository.GoalTriggerRepository

class PushTriggerUseCase(private val goalTriggerRepository: GoalTriggerRepository,
                         private val actionRepository: ActionRepository) {

    @SuppressLint("CheckResult")
    fun pushTrigger(actionId: Int, value: Long) {
        actionRepository.findAction(actionId).subscribe({ action ->

            val trigger: GoalTrigger = when (action.goalState) {
                is GoalState.Count -> GoalTrigger.Count(value.toInt())
                else -> throw Error("Unimplemented")
            }

            goalTriggerRepository.insert(trigger).subscribe()

        }, { throw it }, { throw NoSuchActionException(actionId) })
    }

}