package com.mupper.personlist.data.local

import com.mupper.personlist.PersonQueries
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PersonLocalDataSource {
    fun addPersons(persons: List<LocalPerson>)
    fun getPersons(): Flow<List<LocalPerson>>
}

class PersonLocalDataSourceImpl @Inject constructor(
    private val personQueries: PersonQueries,
) : PersonLocalDataSource {
    override fun addPersons(persons: List<LocalPerson>) {
        persons.forEach(::insertOrReplacePerson)
    }

    private fun insertOrReplacePerson(localPerson: LocalPerson) {
        with(localPerson) {
            personQueries.insertOrReplacePerson(id, firstName, lastName, birthday)
        }
    }

    override fun getPersons(): Flow<List<LocalPerson>> =
        personQueries.getPersons().asFlow().mapToList()
}