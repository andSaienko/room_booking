@file:RequiresApi(Build.VERSION_CODES.O)

package com.room.booking.presentation.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.room.booking.domain.usecase.BookRoomUseCase
import com.room.booking.domain.usecase.GetRoomByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

private const val ROOM_ID = "roomId"

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRoomByIdUseCase: GetRoomByIdUseCase,
    private val bookRoomUseCase: BookRoomUseCase,
) : ViewModel() {

    private val roomId: String = savedStateHandle[ROOM_ID] ?: ""

    private val _state = MutableStateFlow(DetailsUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val roomInfo = getRoomByIdUseCase(roomId)
            _state.update {
                it.copy(
                    room = roomInfo,
                    isBookButtonEnabled =
                        roomInfo.freeFrom == LocalTime.of(9, 0) &&
                                roomInfo.freeUntil == LocalTime.of(18, 0)
                )
            }
        }
    }

    fun bookRoom() {
        viewModelScope.launch {
            bookRoomUseCase(checkNotNull(state.value.room?.id))
        }
        _state.update {
            it.copy(
                isBookButtonEnabled = false,
                isShowSnackBar = true
            )
        }
    }
}