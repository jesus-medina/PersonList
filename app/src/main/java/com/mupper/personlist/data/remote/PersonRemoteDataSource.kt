package com.mupper.personlist.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.*
import javax.inject.Inject

interface PersonRemoteDataSource {
    suspend fun getPersons(): List<RemotePerson>
}

class PersonRemoteDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : PersonRemoteDataSource {
    override suspend fun getPersons(): List<RemotePerson> = httpClient.get(ENDPOINT_PERSONS_LIST)

    companion object {
        private const val ENDPOINT_PERSONS_LIST =
            "https://60846f739b2bed0017040fca.mockapi.io/v1/persons"
    }
}