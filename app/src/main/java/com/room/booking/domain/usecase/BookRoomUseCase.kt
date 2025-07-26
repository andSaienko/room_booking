package com.room.booking.domain.usecase

import com.room.booking.domain.repo.RoomRepository
import javax.inject.Inject

class BookRoomUseCase @Inject constructor(
    private val repo: RoomRepository
) {

    suspend operator fun invoke(id: String) {
        repo.bookRoom(id)
    }
}