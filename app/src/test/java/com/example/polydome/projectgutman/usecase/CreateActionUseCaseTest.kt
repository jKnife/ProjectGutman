package com.example.polydome.projectgutman.usecase

import com.example.polydome.projectgutman.domain.model.Goal
import com.example.polydome.projectgutman.repository.ActionRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

class CreateActionUseCaseTest {
    private val NAME = "action name"
    private val GOAL_TYPE = GoalType.COUNT
    private val GOAL_VALUE: Long = 5
    private val GOAL = Goal.Count(GOAL_VALUE.toInt())

    private lateinit var SUT: CreateActionUseCase
    private lateinit var actionRepository: ActionRepository

    @Before
    fun setup() {
        actionRepository = mockk()
        SUT = CreateActionUseCase(actionRepository)
    }

    @Test
    fun createAction_insertsNewAction() {
        every { actionRepository.insertAction(NAME, GOAL) } returns Completable.complete()

        SUT.createAction(NAME, GOAL_TYPE, GOAL_VALUE).subscribe()

        verify { actionRepository.insertAction(NAME, GOAL) }
    }

}