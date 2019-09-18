package com.example.polydome.projectgutman.usecase

import android.annotation.SuppressLint
import com.example.polydome.projectgutman.domain.model.Goal
import com.example.polydome.projectgutman.domain.model.GoalTrigger
import com.example.polydome.projectgutman.domain.service.GoalService
import com.example.polydome.projectgutman.repository.ActionsRepository

class PushTriggerUseCase(private val actionsRepository: ActionsRepository) {

    @SuppressLint("CheckResult")
    fun pushTrigger(actionId: Int, value: Long) {
        actionsRepository.findAction(actionId).subscribe({ action ->

            val newGoal = GoalService.runTrigger(action.goal as Goal.Count, GoalTrigger.Count(value.toInt()))
            val newAction = action.copy(goal = newGoal)

            actionsRepository.updateAction(newAction)

        }, { throw it }, { throw NoSuchActionException(actionId) })
    }

}