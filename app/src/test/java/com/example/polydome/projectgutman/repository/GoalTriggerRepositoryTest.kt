package com.example.polydome.projectgutman.repository

import com.example.polydome.projectgutman.data.GoalTriggerDao
import com.example.polydome.projectgutman.data.GoalTriggerEntity
import com.example.polydome.projectgutman.data.TriggerType
import com.example.polydome.projectgutman.domain.model.GoalTrigger
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class GoalTriggerRepositoryTest {

    private lateinit var SUT: GoalTriggerRepository
    private lateinit var goalTriggerDao: GoalTriggerDao

    @Before
    fun setup() {
        goalTriggerDao = mockk()
        SUT = GoalTriggerRepository(goalTriggerDao)
    }

    @Test
    fun getByActionId_daoEmitsSingleCollection_returnsObservableFromThatCollection() {
        val ACTION_ID = 32
        val TRIGGERS_ENTITIES_LIST = listOf(
            GoalTriggerEntity(
                id = null,
                actionId = ACTION_ID,
                value = 2,
                triggerType = TriggerType.COUNT
            ),
            GoalTriggerEntity(
                id = null,
                actionId = ACTION_ID,
                value = 8,
                triggerType = TriggerType.COUNT
            )
        )
        val TRIGGERS_LIST = listOf(GoalTrigger.Count(2), GoalTrigger.Count(8))

        every { goalTriggerDao.getByActionId(ACTION_ID) } returns Observable.just(TRIGGERS_ENTITIES_LIST)

        val test = TestObserver<List<GoalTrigger>>()

        SUT.getByActionId(ACTION_ID).subscribe(test)

        test.assertValue(TRIGGERS_LIST)

    }

}