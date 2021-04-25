package com.mupper.personlist.presentation.viewmodel

import com.mupper.personlist.MainCoroutineRule
import com.mupper.personlist.domain.entity.DomainPerson
import com.mupper.personlist.domain.usecase.GetPersonsUseCase
import com.mupper.personlist.domain.usecase.RetrievePersonsUseCase
import com.mupper.personlist.presentation.entity.UIPerson
import com.mupper.personlist.utils.ListMapper
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PersonViewModelImplTest {
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @RelaxedMockK
    lateinit var retrievePersonsUseCase: RetrievePersonsUseCase

    @RelaxedMockK
    lateinit var getPersonsUseCase: GetPersonsUseCase

    @RelaxedMockK
    lateinit var domainPersonToUIPersonListMapper: ListMapper<DomainPerson, UIPerson>

    @InjectMockKs
    lateinit var personViewModelImpl: PersonViewModelImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun retrievePersonsShouldCallInvokeOnRetrievePersonsUseCase() = runBlockingTest {
        // When
        personViewModelImpl.retrievePersons()

        // Then
        coVerify { retrievePersonsUseCase() }
    }

    @Test
    fun getPersonsShouldReturnExpectedListOfPersonsGivenInvokeOnGetPersonsUseCaseReturnsAListWithExpectedDomainPersonAndMapOnDomainPersonToUIPersonListMapperReturnsAListWithExpectedUIPerson() = runBlockingTest {
        // Given
        val expectedDomainPerson = mockk<DomainPerson>()
        every { getPersonsUseCase() } returns flowOf(listOf(expectedDomainPerson))
        val expectedUIPerson = mockk<UIPerson>()
        every { domainPersonToUIPersonListMapper.map(listOf(expectedDomainPerson)) } returns listOf(expectedUIPerson)

        // When
        val result = personViewModelImpl.getPersons().value

        // Then
        assertThat(result, `is`(listOf(expectedUIPerson)))
    }
}