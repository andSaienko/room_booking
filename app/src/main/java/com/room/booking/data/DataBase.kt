package com.room.booking.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.room.booking.data.dao.RoomDao
import com.room.booking.data.entity.RoomEntity
import com.room.booking.data.mapper.TimeMapper

@Database(entities = [RoomEntity::class], version = 1)
@TypeConverters(TimeMapper::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDao
}