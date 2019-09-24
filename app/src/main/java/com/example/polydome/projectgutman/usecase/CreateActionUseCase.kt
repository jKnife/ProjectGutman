package com.example.polydome.projectgutman.usecase

import com.example.polydome.projectgutman.domain.model.Goal
import com.example.polydome.projectgutman.repository.ActionRepository
import io.reactivex.Completable
import javax.inject.Inject

class CreateActionUseCase
@Inject constructor(private val actionRepository: ActionRepository) {

    private fun createGoal(goalType: GoalType, goalTargetValue: Long): Goal {
        return when (goalType) {
            GoalType.COUNT -> Goal.Count(goalTargetValue.toInt())
        }
    }

    fun createAction(name: String, goalType: GoalType, goalTargetValue: Long): Completable =
        actionRepository.insertAction(name, createGoal(goalType, goalTargetValue))


}