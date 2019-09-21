package com.example.polydome.projectgutman.presentation.viewmodel

import com.example.polydome.projectgutman.data.*
import com.example.polydome.projectgutman.usecase.CalculateGoalStateUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class ActionViewModelTest {

    private lateinit var SUT: ActionViewModel
    private lateinit var actionEntityDao: ActionEntityDao
    private lateinit var calculateGoalStateUseCase: CalculateGoalStateUseCase

    @Before
    fun setup() {
        actionEntityDao = mockk()
        calculateGoalStateUseCase = mockk()
        SUT = ActionViewModel(actionEntityDao, calculateGoalStateUseCase)
    }

    @Test
    fun currentValue_validGoalState_emitsProperValue() {
        val ACTION_ID = 11
        val GOAL_STATE = 37L

        every { calculateGoalStateUseCase.calculateGoalState(ACTION_ID) } returns Observable.just(GOAL_STATE)

        val test = TestObserver<String>()

        SUT.switchActionId(ACTION_ID)
        SUT.goalState.subscribe(test)

        val expectedString = GOAL_STATE.toString()
        test.assertValue(expectedString)
    }

}