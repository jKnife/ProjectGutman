package com.example.polydome.projectgutman.repository

import com.example.polydome.projectgutman.data.ActionEntity
import com.example.polydome.projectgutman.data.ActionEntityDao
import com.example.polydome.projectgutman.data.TriggerType
import com.example.polydome.projectgutman.domain.model.Goal
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class GoalRepositoryTest {

    val ID = 13
    val ACTION_ENTITY = ActionEntity(
        id = null,
        currentValue = 0,
        targetValue = 40,
        triggerType = TriggerType.COUNT,
        name = "huu"
    )
    val GOAL: Goal = Goal.Count(
        targetCount = ACTION_ENTITY.targetValue.toInt()
    )

    private lateinit var SUT: GoalRepository
    private lateinit var actionEntityDao: ActionEntityDao

    private lateinit var testObserver: TestObserver<Goal>

    @Before
    fun setup() {
        actionEntityDao = mockk()
        SUT = GoalRepository(actionEntityDao)

        testObserver = TestObserver()
    }

    @Test
    fun getByActionId_daoEmitsSingleAction_emitsProperGoal() {
        every { actionEntityDao.getById(ID) } returns Observable.just(ACTION_ENTITY)

        SUT.getByActionId(ID).subscribe(testObserver)

        testObserver.assertValue(GOAL)
    }

    @Test
    fun findByActionId_noSuchAction_completesWithNoValue() {
        every { actionEntityDao.findById(ID) } returns Maybe.empty()

        SUT.findByActionId(ID).subscribe(testObserver)

        testObserver.assertNoValues()
        testObserver.assertComplete()
    }

    @Test
    fun findByActionId_querySuccessWithAction_successesWithAction() {
        every { actionEntityDao.findById(ID) } returns Maybe.just(ACTION_ENTITY)

        SUT.findByActionId(ID).subscribe(testObserver)

        testObserver.assertValue(GOAL)
        testObserver.assertComplete()
    }

}