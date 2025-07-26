package com.room.booking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.room.booking.data.entity.RoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rooms: List<RoomEntity>)

    @Query("SELECT * FROM meeting_rooms")
    fun getAll(): Flow<List<RoomEntity>>

    @Query("SELECT * FROM meeting_rooms WHERE id = :id")
    suspend fun getRoomById(id: String): RoomEntity

    @Query("UPDATE meeting_rooms SET isBooked = :booked WHERE id = :id")
    suspend fun updateRoom(id: String, booked: Boolean)
}