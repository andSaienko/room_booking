package com.room.booking.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime

@Entity(tableName = "meeting_rooms")
data class RoomEntity(
    @PrimaryKey val id: String,
    val name: String,
    val capacity: Int,
    val freeFrom: LocalTime,
    val freeUntil: LocalTime,
    val isBooked: Boolean
)