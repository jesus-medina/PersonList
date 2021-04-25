package com.mupper.personlist.di

import com.mupper.personlist.domain.usecase.GetPersonsUseCase
import com.mupper.personlist.domain.usecase.GetPersonsUseCaseImpl
import com.mupper.personlist.domain.usecase.RetrievePersonsUseCase
import com.mupper.personlist.domain.usecase.RetrievePersonsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ViewModelModule {

  @Binds
  fun bindsGetPersonsUseCase(
    getPersonsUseCaseImpl: GetPersonsUseCaseImpl
  ): GetPersonsUseCase

  @Binds
  fun bindsRetrievePersonsUseCase(
    retrievePersonsUseCaseImpl: RetrievePersonsUseCaseImpl
  ): RetrievePersonsUseCase
}