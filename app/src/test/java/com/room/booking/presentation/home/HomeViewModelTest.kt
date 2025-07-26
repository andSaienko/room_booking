@file:OptIn(ExperimentalCoroutinesApi::class)

package com.room.booking.presentation.home

import com.room.booking.domain.model.MeetingRoom
import com.room.booking.domain.usecase.GetRoomsInfoUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalTime
import kotlin.test.assertEquals

class HomeViewModelTest {

    private val getRoomsInfoUseCase = mockk<GetRoomsInfoUseCase>()

    private val mockkRoomList = listOf(
        MeetingRoom(
            id = "1",
            name = "Room A",
            capacity = 10,
            freeFrom = LocalTime.of(9, 0),
            freeUntil = LocalTime.of(18, 0),
            isBooked = false
        ),
        MeetingRoom(
            id = "2",
            name = "Room B",
            capacity = 10,
            freeFrom = LocalTime.of(9, 0),
            freeUntil = LocalTime.of(18, 0),
            isBooked = true
        )
    )

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        coEvery { getRoomsInfoUseCase() } returns flowOf(mockkRoomList)

        viewModel = HomeViewModel(
            getRoomsInfoUseCase = getRoomsInfoUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN date from DB loaded THEN show room list`() = runTest {
        advanceUntilIdle()
        val state = viewModel.state.value
        assertEquals(mockkRoomList, state.rooms)
    }

    @Test
    fun `WHEN user click hide booked THEN hide booked`() = runTest {
        advanceUntilIdle()
        viewModel.changeBookedRoomsVisibility()
        val state = viewModel.state.value
        advanceUntilIdle()
        assertFalse(state.isBookedRoomsShown)
    }
}