package com.room.booking.presentation.details

import com.room.booking.domain.model.MeetingRoom

data class DetailsUiState(
    val room: MeetingRoom? = null,
    val isBookButtonEnabled: Boolean = true,
    val isShowSnackBar: Boolean = false,
)