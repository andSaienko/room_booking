package com.room.booking.presentation.home

import com.room.booking.domain.model.MeetingRoom

data class HomeUiState(
    val rooms: List<MeetingRoom> = emptyList(),
    val filteredRooms: List<MeetingRoom> = emptyList(),
    val isBookedRoomsShown: Boolean = true,
)