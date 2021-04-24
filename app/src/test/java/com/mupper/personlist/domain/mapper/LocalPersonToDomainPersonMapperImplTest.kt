package com.mupper.personlist.domain.mapper

import com.mupper.personlist.data.local.LocalPerson
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.random.Random

class LocalPersonToDomainPersonMapperImplTest {
    @RelaxedMockK
    lateinit var localPerson: LocalPerson

    lateinit var localPersonToDomainPersonMapperImpl: LocalPersonToDomainPersonMapperImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        localPersonToDomainPersonMapperImpl = LocalPersonToDomainPersonMapperImpl()
    }

    @Test
    fun mapShouldReturnsDomainPersonWithExpectedIdGivenIdOnLocalPersonReturnsExpectedId() {
        // Given
        val expectedId = "${Random.nextInt()}"
        every { localPerson.id } returns expectedId

        // When
        val result = localPersonToDomainPersonMapperImpl.map(localPerson).id

        // Then
        assertThat(result, `is`(expectedId))
    }

    @Test
    fun mapShouldReturnsDomainPersonWithExpectedFirstNameGivenFirstNameOnLocalPersonReturnsExpectedFirstName() {
        // Given
        val expectedFirstName = "${Random.nextInt()}"
        every { localPerson.firstName } returns expectedFirstName

        // When
        val result = localPersonToDomainPersonMapperImpl.map(localPerson).firstName

        // Then
        assertThat(result, `is`(expectedFirstName))
    }

    @Test
    fun mapShouldReturnsDomainPersonWithExpectedLastNameGivenLastNameOnLocalPersonReturnsExpectedLastName() {
        // Given
        val expectedLastName = "${Random.nextInt()}"
        every { localPerson.lastName } returns expectedLastName

        // When
        val result = localPersonToDomainPersonMapperImpl.map(localPerson).lastName

        // Then
        assertThat(result, `is`(expectedLastName))
    }

    @Test
    fun mapShouldReturnsDomainPersonWithExpectedBirthdayGivenBirthdayOnLocalPersonReturnsExpectedBirthday() {
        // Given
        val expectedBirthday = Date(Random.nextLong())
        every { localPerson.birthday } returns expectedBirthday

        // When
        val result = localPersonToDomainPersonMapperImpl.map(localPerson).birthday

        // Then
        assertThat(result, `is`(expectedBirthday))
    }
}