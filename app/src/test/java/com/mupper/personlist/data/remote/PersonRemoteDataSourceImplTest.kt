package com.mupper.personlist.data.remote

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PersonRemoteDataSourceImplTest {
    lateinit var personRemoteDataSourceImpl: PersonRemoteDataSourceImpl

    @Before
    fun setUp() {
        personRemoteDataSourceImpl =
            PersonRemoteDataSourceImpl(mockHttpClient())
    }

    @Test
    fun getPersonsShouldReturnsExpectedListOfRemotePersonGivenGetOnHttpClientWithPersonsListEndPointReturnsExpectedListOfRemotePerson() =
        runBlocking {
            // Given
            val format = Json { isLenient = true }
            val expectedListOfRemotePerson: List<RemotePerson> = format.decodeFromString(personsJsonList)

            // When
            val result = personRemoteDataSourceImpl.getPersons()

            // Then
            assertThat(result, `is`(expectedListOfRemotePerson))
        }
}

private fun mockHttpClient() = HttpClient(MockEngine) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
    engine {
        addHandler { request ->
            when (request.url.fullUrl) {
                ENDPOINT_PERSONS_LIST -> {
                    val responseHeaders = headersOf("Content-Type" to listOf("${ContentType.Application.Json}"))
                    respond(personsJsonList, headers = responseHeaders)
                }
                else -> error("Unhandled ${request.url.fullUrl}")
            }
        }
    }
}

private val Url.hostWithPortIfRequired: String get() = if (port == protocol.defaultPort) host else hostWithPort
private val Url.fullUrl: String get() = "${protocol.name}://$hostWithPortIfRequired$fullPath"

private const val ENDPOINT_PERSONS_LIST =
    "https://60846f739b2bed0017040fca.mockapi.io/v1/persons"

private val personsJsonList = """
[
{
"id": "1",
"firstName": "James",
"lastName": "Smith",
"birthday": "2020-01-01T10:10:10Z"
},
{
"id": "2",
"firstName": "John",
"lastName": "Johnson",
"birthday": "2020-01-01T10:10:10Z"
},
{
"id": "3",
"firstName": "Robert",
"lastName": "Williams",
"birthday": "2020-01-01T10:10:10Z"
},
{
"id": "4",
"firstName": "Michael",
"lastName": "Brown",
"birthday": "2020-01-01T10:10:10Z"
},
{
"id": "5",
"firstName": "William",
"lastName": "Jones",
"birthday": "2020-01-01T10:10:10Z"
},
{
"id": "6",
"firstName": "David",
"lastName": "Miller",
"birthday": "2020-01-01T10:10:10Z"
},
{
"id": "7",
"firstName": "Richard",
"lastName": "Davis",
"birthday": "2020-01-01T10:10:10Z"
},
{
"id": "8",
"firstName": "Joseph",
"lastName": "Garcia",
"birthday": "2020-01-01T10:10:10Z"
},
{
"id": "9",
"firstName": "Thomas",
"lastName": "Rodriguez",
"birthday": "2020-01-01T10:10:10Z"
},
{
"id": "10",
"firstName": "Charles",
"lastName": "Wilson",
"birthday": "2020-01-01T10:10:10Z"
}
]
""".trimIndent()
