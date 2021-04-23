package com.mupper.personlist.domain.repository

import com.mupper.personlist.domain.entity.DomainPerson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PersonRepository {
    fun retrievePersons()
    fun getPersons(): Flow<List<DomainPerson>>
}

class PersonRepositoryImpl @Inject constructor() : PersonRepository {
    override fun retrievePersons() {
        TODO("Not yet implemented")
    }

    override fun getPersons(): Flow<List<DomainPerson>> {
        TODO("Not yet implemented")
    }
}