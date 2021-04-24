package com.mupper.personlist.data.local

import com.mupper.personlist.Person
import com.mupper.personlist.PersonQueries
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.Flow
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class PersonLocalDataSourceImplTest {
    @RelaxedMockK
    lateinit var localPerson: LocalPerson

    @RelaxedMockK
    lateinit var personQueries: PersonQueries

    @InjectMockKs
    lateinit var personLocalDataSourceImpl: PersonLocalDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getPersonsShouldReturnExpectedListOfPersonsGivenGetPersonsOnPersonQueriesReturnsQueryAndAsFlowOnQueryReturnsFlowAndMapToListOnFlowReturnsExpectedListOfPersons() {
        // Given
        mockkStatic("kotlinx.coroutines.flow.FlowKt")
        mockkStatic("com.squareup.sqldelight.runtime.coroutines.FlowQuery")
        val query = mockk<Query<Person>>()
        val flow = mockk<Flow<Query<Person>>>()
        every { query.asFlow() } returns flow
        val expectedListOfPersons = mockk<Flow<List<LocalPerson>>>()
        every { flow.mapToList() } returns expectedListOfPersons
        every { personQueries.getPersons() } returns query

        // When
        val result = personLocalDataSourceImpl.getPersons()

        // Then
        assertThat(result, `is`(expectedListOfPersons))

        // Finally
        unmockkStatic(Flow::class)
    }

    @Test
    fun addPersonsShouldCallInsertOrReplacePersonWithExpectedIdGivenListOfLocalPersonWithExpectedId() {
        // Given
        val localPerson = mockk<LocalPerson>(relaxed = true)
        val expectedId = Random.nextLong()
        every { localPerson.id } returns expectedId

        // When
        personLocalDataSourceImpl.addPersons(listOf(localPerson))

        // Then
        verify { personQueries.insertOrReplacePerson(expectedId, any(), any(), any()) }
    }

    @Test
    fun addPersonsShouldCallInsertOrReplacePersonWithExpectedFirstNameGivenListOfLocalPersonWithExpectedFirstName() {
        // Given
        val localPerson = mockk<LocalPerson>(relaxed = true)
        val expectedFirstName = "${Random.nextInt()}"
        every { localPerson.firstName } returns expectedFirstName

        // When
        personLocalDataSourceImpl.addPersons(listOf(localPerson))

        // Then
        verify { personQueries.insertOrReplacePerson(any(), expectedFirstName, any(), any()) }
    }

    @Test
    fun addPersonsShouldCallInsertOrReplacePersonWithExpectedLastNameGivenListOfLocalPersonWithExpectedLastName() {
        // Given
        val localPerson = mockk<LocalPerson>(relaxed = true)
        val expectedLastName = "${Random.nextInt()}"
        every { localPerson.lastName } returns expectedLastName

        // When
        personLocalDataSourceImpl.addPersons(listOf(localPerson))

        // Then
        verify { personQueries.insertOrReplacePerson(any(), any(), expectedLastName, any()) }
    }

    @Test
    fun addPersonsShouldCallInsertOrReplacePersonWithExpectedBirthdayGivenListOfLocalPersonWithExpectedBirthday() {
        // Given
        val localPerson = mockk<LocalPerson>(relaxed = true)
        val expectedBirthday = "${Random.nextInt()}"
        every { localPerson.birthday } returns expectedBirthday

        // When
        personLocalDataSourceImpl.addPersons(listOf(localPerson))

        // Then
        verify { personQueries.insertOrReplacePerson(any(), any(), any(), expectedBirthday) }
    }

    @Test
    fun addPersonsShouldCallInsertOrReplacePersonOnPersonQueriesExactlyPersonsAmount() {
        // Given
        val persons = listOf(
            listOf(localPerson, localPerson),
            listOf(localPerson, localPerson, localPerson),
            listOf(localPerson, localPerson, localPerson, localPerson),
        ).random()

        // When
        personLocalDataSourceImpl.addPersons(persons)

        // Then
        val personAmount = persons.size
        verify(exactly = personAmount) {
            personQueries.insertOrReplacePerson(
                any(),
                any(),
                any(),
                any()
            )
        }
    }
}