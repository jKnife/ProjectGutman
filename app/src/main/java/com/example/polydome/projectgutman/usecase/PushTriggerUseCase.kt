package com.example.polydome.projectgutman.usecase

import android.annotation.SuppressLint
import com.example.polydome.projectgutman.domain.model.Goal
import com.example.polydome.projectgutman.domain.model.GoalTrigger
import com.example.polydome.projectgutman.domain.service.GoalService
import com.example.polydome.projectgutman.repository.ActionsRepository
import com.example.polydome.projectgutman.repository.GoalTriggerRepository

class PushTriggerUseCase(private val goalTriggerRepository: GoalTriggerRepository,
                         private val actionsRepository: ActionsRepository) {

    @SuppressLint("CheckResult")
    fun pushTrigger(actionId: Int, value: Long) {
        actionsRepository.findAction(actionId).subscribe({ action ->

            val trigger: GoalTrigger = when (action.goal) {
                is Goal.Count -> GoalTrigger.Count(value.toInt())
                else -> throw Error("Unimplemented")
            }

            goalTriggerRepository.insert(trigger).subscribe()

        }, { throw it }, { throw NoSuchActionException(actionId) })
    }

}