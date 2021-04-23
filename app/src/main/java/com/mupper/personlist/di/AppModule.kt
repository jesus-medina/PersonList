package com.mupper.personlist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  @Provides
  fun providesAppLocale(): Locale = Locale.getDefault()
}