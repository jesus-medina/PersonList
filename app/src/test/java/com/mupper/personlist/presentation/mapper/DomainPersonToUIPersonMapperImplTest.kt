package com.mupper.personlist.presentation.mapper

import com.mupper.personlist.domain.entity.DomainPerson
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

class DomainPersonToUIPersonMapperImplTest {
    @RelaxedMockK
    lateinit var birthdayDateFormat: DateFormat

    @RelaxedMockK
    lateinit var domainPerson: DomainPerson

    @InjectMockKs
    lateinit var domainPersonToUIPersonMapperImpl: DomainPersonToUIPersonMapperImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { domainPerson.firstName } returns "${Random.nextInt()}"
        every { domainPerson.lastName } returns "${Random.nextInt()}"
    }

    @Test
    fun mapShouldReturnUIPersonWithExpectedIdGivenIdOnDomainPersonReturnsExpectedId() {
        // Given
        val expectedId = "${Random.nextInt()}"
        every { domainPerson.id } returns expectedId

        // When
        val result = domainPersonToUIPersonMapperImpl.map(domainPerson).id

        // Then
        assertThat(result, `is`(expectedId))
    }

    @Test
    fun mapShouldReturnUIPersonWithExpectedFirstNameGivenIdOnDomainPersonReturnsExpectedFirstName() {
        // Given
        val expectedFirstName = "${Random.nextInt()}"
        every { domainPerson.firstName } returns expectedFirstName

        // When
        val result = domainPersonToUIPersonMapperImpl.map(domainPerson).firstName

        // Then
        assertThat(result, `is`(expectedFirstName))
    }

    @Test
    fun mapShouldReturnUIPersonWithExpectedLastNameGivenIdOnDomainPersonReturnsExpectedLastName() {
        // Given
        val expectedLastName = "${Random.nextInt()}"
        every { domainPerson.lastName } returns expectedLastName

        // When
        val result = domainPersonToUIPersonMapperImpl.map(domainPerson).lastName

        // Then
        assertThat(result, `is`(expectedLastName))
    }

    @Test
    fun mapShouldReturnUIPersonWithExpectedBirthdayGivenIdOnDomainPersonReturnsExpectedBirthdayAndFormatOnBirthdayFormatReturnsExpectedFormattedBirthday() {
        // Given
        val expectedBirthday = Date(Random.nextLong())
        every { domainPerson.birthday } returns expectedBirthday
        val expectedFormattedBirthday = "${Random.nextLong()}"
        every { birthdayDateFormat.format(expectedBirthday) } returns expectedFormattedBirthday

        // When
        val result = domainPersonToUIPersonMapperImpl.map(domainPerson).birthday

        // Then
        assertThat(result, `is`(expectedFormattedBirthday))
    }
}