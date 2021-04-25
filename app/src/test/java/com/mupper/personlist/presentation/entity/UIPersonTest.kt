package com.mupper.personlist.presentation.entity

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.random.Random

class UIPersonTest {
    lateinit var uiPerson: UIPerson

    @Test
    fun fullNameShouldReturnExpectedFullNameGivenFirstNameConcatenatedWithLastNameConformExpectedFullName() {
        // Given
        val firstName = "${Random.nextInt()}"
        val lastName = "${Random.nextInt()}"
        uiPerson = UIPerson("", firstName, lastName, "")

        // When
        val result = uiPerson.fullName

        // Then
        val expectedFullName = "$firstName $lastName"
        assertThat(result, `is`(expectedFullName))
    }

    @Test
    fun acronymShouldReturnExpectedAcronymGivenFirstNameFirstLatterConcatenatedWithLastNameFirstLaterConformExpectedAcronym() {
        // Given
        val firstName = "${Random.nextInt()}"
        val lastName = "${Random.nextInt()}"
        uiPerson = UIPerson("", firstName, lastName, "")

        // When
        val result = uiPerson.acronym

        // Then
        val expectedAcronym = "${firstName.first()}${lastName.first()}"
        assertThat(result, `is`(expectedAcronym))
    }

    @Test(expected = IllegalArgumentException::class)
    fun createUIPersonShouldThrowIllegalArgumentExceptionGivenFirstNameIsEmpty() {
        // Given
        val firstName = ""

        // When
        uiPerson = UIPerson("", firstName, "" ,"")
    }

    @Test(expected = IllegalArgumentException::class)
    fun createUIPersonShouldThrowIllegalArgumentExceptionGivenLastNameIsEmpty() {
        // Given
        val lastName = ""

        // When
        uiPerson = UIPerson("", "", lastName,"")
    }
}