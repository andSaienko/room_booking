package com.room.booking.domain.usecase

import com.room.booking.domain.model.MeetingRoom
import com.room.booking.domain.repo.RoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoomsInfoUseCase @Inject constructor(
    private val repo: RoomRepository
) {
    suspend operator fun invoke(): Flow<List<MeetingRoom>> {
        return repo.getRooms()
    }
}