package com.mupper.personlist.data.remote

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PersonRemoteDataSource {
    fun getPersons(): Flow<List<RemotePerson>>
}

class PersonRemoteDataSourceImpl @Inject constructor() : PersonRemoteDataSource {
    override fun getPersons(): Flow<List<RemotePerson>> {
        TODO("Not yet implemented")
    }
}