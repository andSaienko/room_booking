package com.room.booking.domain.usecase

import com.room.booking.domain.model.MeetingRoom
import com.room.booking.domain.repo.RoomRepository
import javax.inject.Inject

class GetRoomByIdUseCase @Inject constructor(
    private val repo: RoomRepository
) {
    suspend operator fun invoke(id: String): MeetingRoom {
        return repo.getRoomById(id)
    }
}