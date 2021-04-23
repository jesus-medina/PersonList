package com.mupper.personlist.di

import com.mupper.personlist.data.local.LocalPerson
import com.mupper.personlist.data.remote.RemotePerson
import com.mupper.personlist.domain.entity.DomainPerson
import com.mupper.personlist.domain.mapper.LocalPersonToDomainPersonMapperImpl
import com.mupper.personlist.domain.mapper.RemotePersonToLocalPersonMapper
import com.mupper.personlist.domain.mapper.RemotePersonToLocalPersonMapperImpl
import com.mupper.personlist.presentation.entity.UIPerson
import com.mupper.personlist.presentation.mapper.DomainPersonToUIPersonMapperImpl
import com.mupper.personlist.utils.ListMapper
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
object MapperModule {
    @Provides
    fun providesDomainPersonToUIPersonListMapper(
        domainPersonToUIPersonMapperImpl: DomainPersonToUIPersonMapperImpl
    ): ListMapper<DomainPerson, UIPerson> = ListMapperImpl(domainPersonToUIPersonMapperImpl)

    @Provides
    fun providesRemotePersonToLocalPersonListMapper(
        remotePersonToLocalPersonMapperImpl: RemotePersonToLocalPersonMapperImpl
    ): ListMapper<RemotePerson, LocalPerson> = ListMapperImpl(remotePersonToLocalPersonMapperImpl)

    @Provides
    fun providesLocalPersonToDomainPersonListMapper(
        localPersonToDomainPersonMapperImpl: LocalPersonToDomainPersonMapperImpl
    ): ListMapper<LocalPerson, DomainPerson> = ListMapperImpl(localPersonToDomainPersonMapperImpl)

    @Provides
    fun providesBirthdayDateFormat(
        appLocale: Locale
    ): DateFormat = SimpleDateFormat("M/d/yy", appLocale)
}