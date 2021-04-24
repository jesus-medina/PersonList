package com.mupper.personlist.domain.mapper

import com.mupper.personlist.data.remote.RemotePerson
import com.mupper.personlist.exception.RemoteMappingException
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

class RemotePersonToLocalPersonMapperImplTest {
    @RelaxedMockK
    lateinit var dateFormat: DateFormat

    @RelaxedMockK
    lateinit var remotePerson: RemotePerson

    @InjectMockKs
    lateinit var remotePersonToLocalPersonMapperImpl: RemotePersonToLocalPersonMapperImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun mapShouldReturnLocalPersonWithExpectedIdGivenIdOnRemotePersonReturnsExpectedId() {
        // Given
        val expectedId = "${Random.nextInt()}"
        every { remotePerson.id } returns expectedId

        // When
        val result = remotePersonToLocalPersonMapperImpl.map(remotePerson).id

        // Then
        assertThat(result, `is`(expectedId))
    }

    @Test
    fun mapShouldReturnLocalPersonWithExpectedFirstNameGivenFirstNameOnRemotePersonReturnsExpectedFirstName() {
        // Given
        val expectedFirstName = "${Random.nextInt()}"
        every { remotePerson.firstName } returns expectedFirstName

        // When
        val result = remotePersonToLocalPersonMapperImpl.map(remotePerson).firstName

        // Then
        assertThat(result, `is`(expectedFirstName))
    }

    @Test
    fun mapShouldReturnLocalPersonWithExpectedLastNameGivenLastNameOnRemotePersonReturnsExpectedLastName() {
        // Given
        val expectedLastName = "${Random.nextInt()}"
        every { remotePerson.lastName } returns expectedLastName

        // When
        val result = remotePersonToLocalPersonMapperImpl.map(remotePerson).lastName

        // Then
        assertThat(result, `is`(expectedLastName))
    }

    @Test
    fun mapShouldReturnLocalPersonWithExpectedBirthdayGivenBirthdayOnRemotePersonReturnsBirthdayAndParseOnDateFormatWithBirthdayReturnsExpectedBirthday() {
        // Given
        val birthday = "${Random.nextInt()}"
        every { remotePerson.birthday } returns birthday
        val expectedBirthday = Date(Random.nextLong())
        every { dateFormat.parse(birthday) } returns expectedBirthday

        // When
        val result = remotePersonToLocalPersonMapperImpl.map(remotePerson).birthday

        // Then
        assertThat(result, `is`(expectedBirthday))
    }

    @Test(expected = RemoteMappingException::class)
    fun mapShouldThrowRemoteMappingExceptionGivenIdOnRemotePersonReturnsNull() {
        // Given
        every { remotePerson.id } returns null

        // When
        remotePersonToLocalPersonMapperImpl.map(remotePerson)
    }

    @Test(expected = RemoteMappingException::class)
    fun mapShouldThrowRemoteMappingExceptionGivenFirstNameOnRemotePersonReturnsNull() {
        // Given
        every { remotePerson.firstName } returns null

        // When
        remotePersonToLocalPersonMapperImpl.map(remotePerson)
    }

    @Test(expected = RemoteMappingException::class)
    fun mapShouldThrowRemoteMappingExceptionGivenLastNameOnRemotePersonReturnsNull() {
        // Given
        every { remotePerson.lastName } returns null

        // When
        remotePersonToLocalPersonMapperImpl.map(remotePerson)
    }

    @Test(expected = RemoteMappingException::class)
    fun mapShouldThrowRemoteMappingExceptionGivenBirthdayOnRemotePersonReturnsNull() {
        // Given
        every { remotePerson.birthday } returns null

        // When
        remotePersonToLocalPersonMapperImpl.map(remotePerson)
    }

    @Test(expected = RemoteMappingException::class)
    fun mapShouldThrowRemoteMappingExceptionGivenParseOnDateFormatReturnsNull() {
        // Given
        every { dateFormat.parse(any()) } returns null

        // When
        remotePersonToLocalPersonMapperImpl.map(remotePerson)
    }
}