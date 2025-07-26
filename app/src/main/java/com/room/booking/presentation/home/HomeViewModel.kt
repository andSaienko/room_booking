package com.room.booking.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.room.booking.domain.model.MeetingRoom
import com.room.booking.domain.usecase.GetRoomsInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRoomsInfoUseCase: GetRoomsInfoUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getRoomsInfoUseCase().collect { list ->
                _state.update {
                    it.copy(
                        rooms = list,
                        filteredRooms = applyFilter(list, _state.value.isBookedRoomsShown)
                    )
                }
            }
        }
    }

    fun changeBookedRoomsVisibility() {
        _state.update { state ->
            state.copy(
                filteredRooms = applyFilter(state.rooms, !state.isBookedRoomsShown),
                isBookedRoomsShown = !state.isBookedRoomsShown
            )
        }
    }

    private fun applyFilter(
        rooms: List<MeetingRoom>,
        isBookedShown: Boolean
    ): List<MeetingRoom> {
        return rooms.filter { room ->
            !room.isBooked || isBookedShown
        }
    }
}