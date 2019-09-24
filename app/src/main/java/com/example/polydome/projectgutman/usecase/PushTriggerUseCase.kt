package com.example.polydome.projectgutman.usecase

import com.example.polydome.projectgutman.domain.model.Goal
import com.example.polydome.projectgutman.domain.model.GoalTrigger
import com.example.polydome.projectgutman.repository.GoalRepository
import com.example.polydome.projectgutman.repository.GoalTriggerRepository
import io.reactivex.Completable
import javax.inject.Inject

class PushTriggerUseCase
@Inject constructor(private val goalTriggerRepository: GoalTriggerRepository,
                    private val goalRepository: GoalRepository) {

    private fun createTrigger(goal: Goal, value: Long): GoalTrigger {
        return when (goal) {
            is Goal.Count -> GoalTrigger.Count(value.toInt())
        }

    }

    fun pushTrigger(actionId: Int, value: Long): Completable =
        goalRepository.getByActionId(actionId)
            .firstOrError()
            .doOnError { throw NoSuchActionException(actionId) }
            .flatMapCompletable {
                goalTriggerRepository.insert(actionId, createTrigger(it, value))
            }

}