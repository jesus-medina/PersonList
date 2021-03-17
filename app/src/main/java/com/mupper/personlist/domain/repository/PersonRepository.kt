package com.mupper.personlist.domain.repository

import com.mupper.personlist.domain.entity.DomainPerson
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun retrievePersons()
    fun getPersons(): Flow<List<DomainPerson>>
}