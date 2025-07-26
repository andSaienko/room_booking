package com.room.booking.data.repo

import com.room.booking.data.dao.RoomDao
import com.room.booking.data.mapper.toDomain
import com.room.booking.domain.model.MeetingRoom
import com.room.booking.domain.repo.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val dao: RoomDao
) : RoomRepository {
    override suspend fun getRooms(): Flow<List<MeetingRoom>> {
        return dao.getAll().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getRoomById(id: String): MeetingRoom {
        return dao.getRoomById(id).toDomain()
    }

    override suspend fun bookRoom(id: String): Result<Unit> {
        return kotlin.runCatching {
            dao.updateRoom(
                id = id,
                booked = true,
            )
        }
    }
}