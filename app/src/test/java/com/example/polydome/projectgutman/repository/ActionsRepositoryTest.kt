package com.example.polydome.projectgutman.repository

import com.example.polydome.projectgutman.data.ActionEntity
import com.example.polydome.projectgutman.data.ActionEntityDao
import com.example.polydome.projectgutman.data.TriggerType
import com.example.polydome.projectgutman.domain.model.Goal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

class ActionsRepositoryTest {

    private lateinit var SUT: ActionsRepository
    private lateinit var actionEntityDao: ActionEntityDao

    @Before
    fun setup() {
        actionEntityDao = mockk()
        SUT = ActionsRepository(actionEntityDao)
    }

    @Test
    fun insert_countGoal_insertsProperEntityWithDao() {
        val NAME = "test action"
        val TARGET_COUNT = 30
        val GOAL: Goal = Goal.Count(TARGET_COUNT)

        val expectedEntity = ActionEntity(
            id = 0,
            name = NAME,
            targetValue = TARGET_COUNT.toLong(),
            currentValue = 0,
            triggerType = TriggerType.COUNT
        )

        every { actionEntityDao.insertAction(expectedEntity) } returns Completable.complete()

        SUT.insertAction(NAME, GOAL).subscribe()

        verify { actionEntityDao.insertAction(expectedEntity) }
    }

}