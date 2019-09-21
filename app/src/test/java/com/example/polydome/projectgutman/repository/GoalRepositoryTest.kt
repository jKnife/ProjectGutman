package com.example.polydome.projectgutman.repository

import com.example.polydome.projectgutman.data.ActionEntity
import com.example.polydome.projectgutman.data.ActionEntityDao
import com.example.polydome.projectgutman.data.TriggerType
import com.example.polydome.projectgutman.domain.model.Difficulty
import com.example.polydome.projectgutman.domain.model.Goal
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class GoalRepositoryTest {

    private lateinit var SUT: GoalRepository
    private lateinit var actionEntityDao: ActionEntityDao

    @Before
    fun setup() {
        actionEntityDao = mockk()
        SUT = GoalRepository(actionEntityDao)
    }

    @Test
    fun getByActionId_daoEmitsSingleAction_emitsProperGoal() {
        val ID = 13
        val ACTION_ENTITY = ActionEntity(
            id = null,
            currentValue = 20,
            targetValue = 40,
            triggerType = TriggerType.COUNT,
            name = "huu",
            difficulty = Difficulty.EASY
        )

        every { actionEntityDao.getById(ID) } returns Observable.just(ACTION_ENTITY)

        val test = TestObserver<Goal>()

        SUT.getByActionId(ID).subscribe(test)

        val expectedGoal: Goal = Goal.Count(
            current = 0,
            target = ACTION_ENTITY.targetValue.toInt()
        )

        test.assertValue(expectedGoal)
    }

}