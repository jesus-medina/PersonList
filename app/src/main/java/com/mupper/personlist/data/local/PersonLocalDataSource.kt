package com.mupper.personlist.data.local

import kotlinx.coroutines.flow.Flow

interface PersonLocalDataSource {
    fun addPersons(persons: List<LocalPerson>)
    fun getPersons(): Flow<List<LocalPerson>>
}