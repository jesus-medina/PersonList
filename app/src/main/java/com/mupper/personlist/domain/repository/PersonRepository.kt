package com.mupper.personlist.domain.repository

import com.mupper.personlist.data.local.LocalPerson
import com.mupper.personlist.data.local.PersonLocalDataSource
import com.mupper.personlist.data.remote.PersonRemoteDataSource
import com.mupper.personlist.data.remote.RemotePerson
import com.mupper.personlist.domain.entity.DomainPerson
import com.mupper.personlist.domain.mapper.LocalPersonToDomainPersonMapper
import com.mupper.personlist.domain.mapper.RemotePersonToLocalPersonMapper
import com.mupper.personlist.utils.ListMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface PersonRepository {
    suspend fun retrievePersons()
    fun getPersons(): Flow<List<DomainPerson>>
}

class PersonRepositoryImpl @Inject constructor(
    private var personLocalDataSource: PersonLocalDataSource,
    private var personRemoteDataSource: PersonRemoteDataSource,
    private var remotePersonToLocalPersonListMapper: RemotePersonToLocalPersonMapper,
    private var localPersonToDomainPersonListMapper: ListMapper<LocalPerson, DomainPerson>
) : PersonRepository {
    override suspend fun retrievePersons() {
        personRemoteDataSource.getPersons()
            .map(remotePersonToLocalPersonListMapper::map)
            .also(personLocalDataSource::addPersons)
    }

    override fun getPersons(): Flow<List<DomainPerson>> =
        personLocalDataSource.getPersons()
            .map(localPersonToDomainPersonListMapper::map)
}