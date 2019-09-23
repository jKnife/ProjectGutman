package com.example.polydome.projectgutman.domain.service

import com.example.polydome.projectgutman.domain.model.GoalState
import com.example.polydome.projectgutman.domain.model.GoalTrigger

object GoalStateService {

    fun runTrigger(goalState: GoalState.Count, trigger: GoalTrigger.Count): GoalState.Count {
        return goalState.copy(currentCount = goalState.currentCount + trigger.count)
    }

}