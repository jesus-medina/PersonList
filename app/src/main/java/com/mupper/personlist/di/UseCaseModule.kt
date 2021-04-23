package com.mupper.personlist.di

import com.mupper.personlist.domain.repository.PersonRepository
import com.mupper.personlist.domain.repository.PersonRepositoryImpl
import com.mupper.personlist.presentation.mapper.DomainPersonToUIPersonMapperImpl
import com.mupper.personlist.utils.ListMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun providesPersonRepository(
        personRepositoryImpl: PersonRepositoryImpl
    ): PersonRepository = personRepositoryImpl
}