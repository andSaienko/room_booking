package com.room.booking.domain.repo

import com.room.booking.domain.model.MeetingRoom
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    suspend fun getRooms(): Flow<List<MeetingRoom>>
    suspend fun getRoomById(id: String): MeetingRoom
    suspend fun bookRoom(id: String): Result<Unit>
}