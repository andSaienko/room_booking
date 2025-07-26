package com.room.booking.data.mapper

import com.room.booking.data.entity.RoomEntity
import com.room.booking.domain.model.MeetingRoom

fun RoomEntity.toDomain(): MeetingRoom =
    MeetingRoom(
        id = id,
        name = name,
        capacity = capacity,
        freeFrom = freeFrom,
        freeUntil = freeUntil,
        isBooked = isBooked
    )

fun MeetingRoom.toEntity(): RoomEntity =
    RoomEntity(
        id = id,
        name = name,
        capacity = capacity,
        freeFrom = freeFrom,
        freeUntil = freeUntil,
        isBooked = isBooked
    )