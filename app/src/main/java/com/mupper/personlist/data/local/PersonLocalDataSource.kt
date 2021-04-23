package com.mupper.personlist.data.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PersonLocalDataSource {
    fun addPersons(persons: List<LocalPerson>)
    fun getPersons(): Flow<List<LocalPerson>>
}

class PersonLocalDataSourceImpl @Inject constructor() : PersonLocalDataSource {
    override fun addPersons(persons: List<LocalPerson>) {
        TODO("Not yet implemented")
    }

    override fun getPersons(): Flow<List<LocalPerson>> {
        TODO("Not yet implemented")
    }
}