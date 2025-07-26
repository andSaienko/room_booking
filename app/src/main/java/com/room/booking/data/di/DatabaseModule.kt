@file:RequiresApi(Build.VERSION_CODES.O)

package com.room.booking.data.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import androidx.room.RoomDatabase
import com.room.booking.data.AppDatabase
import com.room.booking.data.dao.RoomDao
import com.room.booking.data.entity.RoomEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@RequiresApi(Build.VERSION_CODES.O)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration(false)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        INSTANCE?.roomDao()?.insertAll(mockRooms())
                    }
                }
            })
            .build()

        INSTANCE = db
        return db
    }

    @Provides
    @Singleton
    fun provideMeetingRoomDao(database: AppDatabase): RoomDao = database.roomDao()

    private var INSTANCE: AppDatabase? = null
}

private fun mockRooms() = listOf(
    RoomEntity(
        id = "1",
        name = "Meeting Room A",
        capacity = 10,
        freeFrom = LocalTime.of(17, 0),
        freeUntil = LocalTime.of(18, 0),
        isBooked = false
    ),
    RoomEntity(
        id = "2",
        name = "Meeting Room B",
        capacity = 13,
        freeFrom = LocalTime.of(9, 0),
        freeUntil = LocalTime.of(18, 0),
        isBooked = false
    ),
    RoomEntity(
        id = "3",
        name = "Meeting Room C",
        capacity = 6,
        freeFrom = LocalTime.of(12, 0),
        freeUntil = LocalTime.of(18, 0),
        isBooked = false
    ),
    RoomEntity(
        id = "4",
        name = "Meeting Room D",
        capacity = 18,
        freeFrom = LocalTime.of(9, 0),
        freeUntil = LocalTime.of(18, 0),
        isBooked = true
    ),
    RoomEntity(
        id = "5",
        name = "Meeting Room E",
        capacity = 2,
        freeFrom = LocalTime.of(9, 0),
        freeUntil = LocalTime.of(18, 0),
        isBooked = false
    ),
    RoomEntity(
        id = "6",
        name = "Meeting Room F",
        capacity = 10,
        freeFrom = LocalTime.of(9, 0),
        freeUntil = LocalTime.of(18, 0),
        isBooked = true
    ),
    RoomEntity(
        id = "7",
        name = "Meeting Room G",
        capacity = 20,
        freeFrom = LocalTime.of(9, 0),
        freeUntil = LocalTime.of(18, 0),
        isBooked = false
    ),
)