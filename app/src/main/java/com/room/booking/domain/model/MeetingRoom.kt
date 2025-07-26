package com.room.booking.domain.model

import java.time.LocalTime

data class MeetingRoom(
    val id: String,
    val name: String,
    val capacity: Int,
    val freeFrom: LocalTime,
    val freeUntil: LocalTime,
    val isBooked: Boolean
)