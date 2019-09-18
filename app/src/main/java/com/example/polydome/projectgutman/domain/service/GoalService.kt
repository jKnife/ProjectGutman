package com.example.polydome.projectgutman.domain.service

import com.example.polydome.projectgutman.domain.model.Goal
import com.example.polydome.projectgutman.domain.model.GoalTrigger

object GoalService {

    fun runTrigger(goal: Goal.Count, trigger: GoalTrigger.Count): Goal.Count {
        return goal.copy(current = goal.current + trigger.count)
    }

}