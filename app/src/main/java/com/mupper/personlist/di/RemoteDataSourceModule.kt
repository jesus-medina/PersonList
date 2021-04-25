package com.mupper.personlist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {
    @Provides
    fun providesHttpClient(): HttpClient = HttpClient(CIO)
}