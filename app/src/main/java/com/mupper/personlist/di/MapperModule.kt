package com.mupper.personlist.di

import com.mupper.personlist.presentation.mapper.DomainPersonToUIPersonMapper
import com.mupper.personlist.presentation.mapper.DomainPersonToUIPersonMapperImpl
import com.mupper.personlist.utils.ListMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object MapperModule {
  @Provides
  fun providesDomainPersonToUIPersonListMapper(
    domainPersonToUIPersonMapperImpl: DomainPersonToUIPersonMapperImpl
  ) = ListMapperImpl(domainPersonToUIPersonMapperImpl)
}