@file:OptIn(ExperimentalCoroutinesApi::class)

package com.room.booking.presentation.details

import androidx.lifecycle.SavedStateHandle
import com.room.booking.domain.model.MeetingRoom
import com.room.booking.domain.usecase.BookRoomUseCase
import com.room.booking.domain.usecase.GetRoomByIdUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalTime

class DetailsViewModelTest {

    private val getRoomByIdUseCase = mockk<GetRoomByIdUseCase>()
    private val bookRoomUseCase = mockk<BookRoomUseCase>()
    private val savedStateHandle = mockk<SavedStateHandle>().apply {
        every { get<String>("roomId") } returns "1"
    }
    private val mockkRoom = MeetingRoom(
        id = "1",
        name = "Room A",
        capacity = 10,
        freeFrom = LocalTime.of(9, 0),
        freeUntil = LocalTime.of(18, 0),
        isBooked = false
    )

    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        coEvery { getRoomByIdUseCase("1") } returns mockkRoom
        coEvery { bookRoomUseCase(any()) } returns Unit

        viewModel = DetailsViewModel(
            savedStateHandle = savedStateHandle,
            getRoomByIdUseCase = getRoomByIdUseCase,
            bookRoomUseCase = bookRoomUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN date from DB loaded THEN show room details`() = runTest {
        advanceUntilIdle()
        val state = viewModel.state.value
        assertEquals(mockkRoom, state.room)
        assertTrue(state.isBookButtonEnabled)
    }

    @Test
    fun `WHEN user click book THEN room booked`() = runTest {
        advanceUntilIdle()
        viewModel.bookRoom()
        advanceUntilIdle()
        coVerify { bookRoomUseCase("1") }
        val state = viewModel.state.value
        assertFalse(state.isBookButtonEnabled)
        assertTrue(state.isShowSnackBar)
    }
}