package com.mupper.personlist.di

import com.mupper.personlist.data.local.PersonLocalDataSource
import com.mupper.personlist.data.local.PersonLocalDataSourceImpl
import com.mupper.personlist.data.remote.PersonRemoteDataSource
import com.mupper.personlist.data.remote.PersonRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun providesPersonLocalDataSource(
        personLocalDataSourceImpl: PersonLocalDataSourceImpl
    ): PersonLocalDataSource = personLocalDataSourceImpl

    @Provides
    fun providesPersonRemoteDataSource(
        personRemoteDataSourceImpl: PersonRemoteDataSourceImpl
    ): PersonRemoteDataSource = personRemoteDataSourceImpl
}