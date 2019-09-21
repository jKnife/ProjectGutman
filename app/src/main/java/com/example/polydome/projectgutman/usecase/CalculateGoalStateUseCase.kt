package com.example.polydome.projectgutman.usecase

import com.example.polydome.projectgutman.domain.model.Goal
import com.example.polydome.projectgutman.domain.model.GoalTrigger
import com.example.polydome.projectgutman.domain.service.GoalService
import com.example.polydome.projectgutman.repository.GoalRepository
import com.example.polydome.projectgutman.repository.GoalTriggerRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class CalculateGoalStateUseCase
@Inject constructor(private val goalRepository: GoalRepository,
                    private val goalTriggerRepository: GoalTriggerRepository) {

    private fun runTriggerCombiner(goal: Goal, triggers: List<GoalTrigger>): Long {
        val goalFromTriggers = triggers.fold(goal, { acc, trigger ->
            GoalService.runTrigger(acc as Goal.Count, trigger as GoalTrigger.Count)
        })

        return (goalFromTriggers as Goal.Count).current.toLong()
    }

    fun calculateGoalState(actionId: Int): Observable<Long> =
        Observable.combineLatest(
            goalRepository.getByActionId(actionId),
            goalTriggerRepository.getByActionId(actionId),
            BiFunction(this::runTriggerCombiner)
        )
}
