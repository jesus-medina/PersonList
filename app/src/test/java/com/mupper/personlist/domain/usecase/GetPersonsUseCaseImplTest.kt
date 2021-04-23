package com.mupper.personlist.domain.usecase

import com.mupper.personlist.domain.entity.DomainPerson
import com.mupper.personlist.domain.repository.PersonRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test

class GetPersonsUseCaseImplTest {
    @MockK
    lateinit var personRepository: PersonRepository

    @InjectMockKs
    lateinit var getPersonsUseCaseImpl: GetPersonsUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun invokeShouldReturnsExpectedFlowGivenGetPersonsOnPersonRepositoryReturnsExpectedFlow() {
        // Given
        val expectedFlow = mockk<Flow<List<DomainPerson>>>()
        every { personRepository.getPersons() } returns expectedFlow

        // When
        val result = getPersonsUseCaseImpl()

        // Then
        assertThat(result, `is`(expectedFlow))
    }
}