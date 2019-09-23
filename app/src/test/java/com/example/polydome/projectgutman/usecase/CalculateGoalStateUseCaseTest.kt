package com.example.polydome.projectgutman.usecase

import com.example.polydome.projectgutman.domain.model.Goal
import com.example.polydome.projectgutman.domain.model.GoalState
import com.example.polydome.projectgutman.domain.model.GoalTrigger
import com.example.polydome.projectgutman.repository.GoalRepository
import com.example.polydome.projectgutman.repository.GoalTriggerRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class CalculateGoalStateUseCaseTest {

    private lateinit var SUT: CalculateGoalStateUseCase
    private lateinit var goalRepository: GoalRepository
    private lateinit var goalTriggerRepository: GoalTriggerRepository

    val ID = 33
    val GOAL = Goal.Count(40)
    val TRIGGER_ONE = GoalTrigger.Count(28)
    val TRIGGER_TWO = GoalTrigger.Count(41)


    @Before
    fun setup() {
        goalRepository = mockk()
        goalTriggerRepository = mockk()
        SUT = CalculateGoalStateUseCase(goalRepository, goalTriggerRepository)
    }

    @Test
    fun calculateGoalState_emittedSingleGoalAndTrigger_emitsProperState() {
        every { goalRepository.getByActionId(ID) } returns Observable.just(GOAL)
        every { goalTriggerRepository.getByActionId(ID) } returns Observable.just(listOf(TRIGGER_ONE))

        val test = TestObserver<Long>()

        SUT.calculateGoalState(ID).subscribe(test)

        test.assertValue(28)
    }

    @Test
    fun calculateGoalState_emittedSingleGoalAndTwoTriggers_emitsProperState() {
        every { goalRepository.getByActionId(ID) } returns Observable.just(GOAL)
        every { goalTriggerRepository.getByActionId(ID) } returns Observable.just(listOf(TRIGGER_ONE, TRIGGER_TWO))

        val test = TestObserver<Long>()

        SUT.calculateGoalState(ID).subscribe(test)

        test.assertValue(69)
    }

}