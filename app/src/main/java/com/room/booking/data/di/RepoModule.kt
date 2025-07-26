package com.room.booking.data.di

import com.room.booking.data.repo.RoomRepositoryImpl
import com.room.booking.domain.repo.RoomRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindRoomRepo(impl: RoomRepositoryImpl): RoomRepository
}