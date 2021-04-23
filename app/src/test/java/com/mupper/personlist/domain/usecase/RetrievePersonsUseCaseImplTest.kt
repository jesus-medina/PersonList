package com.mupper.personlist.domain.usecase

import com.mupper.personlist.domain.repository.PersonRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RetrievePersonsUseCaseImplTest {
    @RelaxedMockK
    lateinit var personRepository: PersonRepository

    @InjectMockKs
    lateinit var retrievePersonsUseCaseImpl: RetrievePersonsUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun invokeShouldCallRetrievePersonsOnPersonRepository() = runBlockingTest {
        // When
        retrievePersonsUseCaseImpl()

        // Then
        verify { personRepository.retrievePersons() }
    }
}