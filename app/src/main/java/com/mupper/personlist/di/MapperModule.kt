package com.mupper.personlist.di

import com.mupper.personlist.presentation.mapper.DomainPersonToUIPersonMapperImpl
import com.mupper.personlist.utils.ListMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Module
@InstallIn(ViewModelComponent::class)
object MapperModule {
  @Provides
  fun providesDomainPersonToUIPersonListMapper(
    domainPersonToUIPersonMapperImpl: DomainPersonToUIPersonMapperImpl
  ) = ListMapperImpl(domainPersonToUIPersonMapperImpl)

  @Provides
  fun providesBirthdayDateFormat(
    appLocale: Locale
  ): DateFormat = SimpleDateFormat("M/d/yy", appLocale)
}