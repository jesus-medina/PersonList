package com.mupper.personlist.domain.mapper

import com.mupper.personlist.data.local.LocalPerson
import com.mupper.personlist.exception.LocalMappingException
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import java.text.DateFormat
import java.util.*
import kotlin.random.Random

class LocalPersonToDomainPersonMapperImplTest {
    @RelaxedMockK
    lateinit var dateFormat: DateFormat

    @RelaxedMockK
    lateinit var localPerson: LocalPerson

    @InjectMockKs
    lateinit var localPersonToDomainPersonMapperImpl: LocalPersonToDomainPersonMapperImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun mapShouldReturnsDomainPersonWithExpectedIdGivenIdOnLocalPersonReturnsExpectedId() {
        // Given
        val expectedId = Random.nextLong()
        every { localPerson.id } returns expectedId

        // When
        val result = localPersonToDomainPersonMapperImpl.map(localPerson).id

        // Then
        assertThat(result, `is`("$expectedId"))
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
    fun mapShouldReturnsDomainPersonWithExpectedBirthdayGivenBirthdayOnLocalPersonReturnsBirthdayAndParseOnDateFormatReturnsExpectedBirthday() {
        // Given
        val birthday = "${Random.nextInt()}"
        every { localPerson.birthday } returns birthday
        val expectedBirthday = Date(Random.nextLong())
        every { dateFormat.parse(birthday) } returns expectedBirthday

        // When
        val result = localPersonToDomainPersonMapperImpl.map(localPerson).birthday

        // Then
        assertThat(result, `is`(expectedBirthday))
    }

    @Test(expected = LocalMappingException::class)
    fun mapShouldThrowLocalMappingExceptionGivenParseOnDateFormatReturnsNull() {
        // Given
        every { dateFormat.parse(any()) } returns null

        // When
        localPersonToDomainPersonMapperImpl.map(localPerson)
    }
}