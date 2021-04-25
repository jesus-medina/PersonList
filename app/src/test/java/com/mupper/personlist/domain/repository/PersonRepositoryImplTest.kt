package com.mupper.personlist.domain.repository

import com.mupper.personlist.data.local.LocalPerson
import com.mupper.personlist.data.local.PersonLocalDataSource
import com.mupper.personlist.data.remote.PersonRemoteDataSource
import com.mupper.personlist.data.remote.RemotePerson
import com.mupper.personlist.domain.entity.DomainPerson
import com.mupper.personlist.domain.mapper.RemotePersonToLocalPersonMapper
import com.mupper.personlist.utils.ListMapper
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PersonRepositoryImplTest {
    @RelaxedMockK
    lateinit var personLocalDataSource: PersonLocalDataSource

    @RelaxedMockK
    lateinit var personRemoteDataSource: PersonRemoteDataSource

    @RelaxedMockK
    lateinit var remotePersonToLocalPersonMapper: RemotePersonToLocalPersonMapper

    @RelaxedMockK
    lateinit var localPersonToDomainPersonListMapper: ListMapper<LocalPerson, DomainPerson>

    @InjectMockKs
    lateinit var personRepositoryImpl: PersonRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun retrievePersonsShouldCallAddPersonsWithExpectedListOfLocalPersonGivenGetPersonOnPersonRemoteDataSourceReturnsRemotePersonAndMapOnRemotePersonToLocalPersonMapperWithListOfRemotePersonReturnsLocalPerson() =
        runBlockingTest {
            // Given
            val remotePerson = mockk<RemotePerson>()
            coEvery { personRemoteDataSource.getPersons() } returns listOf(remotePerson)
            val localPerson = mockk<LocalPerson>()
            every { remotePersonToLocalPersonMapper.map(remotePerson) } returns localPerson

            // When
            personRepositoryImpl.retrievePersons()

            // Then
            val expectedListOfLocalPerson = listOf(localPerson)
            verify { personLocalDataSource.addPersons(expectedListOfLocalPerson) }
        }

    @Test
    fun getPersonsShouldReturnExpectedListOfDomainPersonGivenGetPersonsOnPersonLocalDataSourceReturnsFlowOfListOfDomainPersonAndMapOnLocalPersonToDomainPersonListMapperReturnsExpectedListOfDomainPerson() =
        runBlockingTest {
            // Given
            val listOfLocalPerson = listOf(mockk<LocalPerson>())
            val flowOfListOfDomainPerson = flowOf(listOfLocalPerson)
            every { personLocalDataSource.getPersons() } returns flowOfListOfDomainPerson
            val expectedListOfDomainPerson = listOf(mockk<DomainPerson>())
            every { localPersonToDomainPersonListMapper.map(listOfLocalPerson) } returns expectedListOfDomainPerson

            // When
            val result = personRepositoryImpl.getPersons().first()

            // Then
            assertThat(result, `is`(expectedListOfDomainPerson))
        }
}