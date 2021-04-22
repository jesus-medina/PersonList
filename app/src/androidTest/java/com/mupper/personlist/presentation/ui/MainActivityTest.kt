package com.mupper.personlist.presentation.ui

import android.content.Context
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.platform.app.InstrumentationRegistry
import com.mupper.personlist.R
import com.mupper.personlist.matchesWithText
import com.mupper.personlist.matchesWithTextColor
import com.mupper.personlist.presentation.entity.UIPerson
import com.mupper.personlist.presentation.viewmodel.PersonViewModelImpl
import com.mupper.personlist.withRecyclerView
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    var personViewModel: PersonViewModelImpl = mockk(relaxed = true)

    lateinit var context: Context

    @Before
    fun setUp() {
        hiltRule.inject()
        context = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun personsRecyclerViewAtFirstPositionOnViewPersonFullNameTextViewShouldMatchesWithExpectedFullNameGivenGetPersonsOnPersonViewModelReturnsAListOfPersonsWithExpectedFullName() {
        // Given
        val expectedFullName = "${Random.nextInt()}"
        val person = mockk<UIPerson>(relaxed = true) {
            every { fullName } returns expectedFullName
        }
        every { personViewModel.getPersons() } returns MutableStateFlow(listOf(person))

        // When
        launchActivity<MainActivity>()

        // Then
        onView(
            withRecyclerView(R.id.personsRecyclerView).atPositionOnView(
                0,
                R.id.personFullNameTextView
            )
        ) matchesWithText expectedFullName
    }

    @Test
    fun personsRecyclerViewAtFirstPositionOnViewAcronymTextViewShouldMatchesWithExpectedAcronymGivenGetPersonsOnPersonViewModelReturnsAListOfPersonsWithExpectedAcronym() {
        // Given
        val expectedAcronym = "${Random.nextInt()}"
        val person = mockk<UIPerson>(relaxed = true) {
            every { acronym } returns expectedAcronym
        }
        every { personViewModel.getPersons() } returns MutableStateFlow(listOf(person))

        // When
        launchActivity<MainActivity>()

        // Then
        onView(
            withRecyclerView(R.id.personsRecyclerView).atPositionOnView(
                0,
                R.id.acronymTextView
            )
        ) matchesWithText expectedAcronym
    }

    @Test
    fun personsRecyclerViewAtFirstPositionOnViewBirthdayTextViewShouldMatchesWithExpectedBirthdayGivenGetPersonsOnPersonViewModelReturnsAListOfPersonsWithExpectedBirthday() {
        // Given
        val expectedBirthday = "${Random.nextInt()}"
        val person = mockk<UIPerson>(relaxed = true) {
            every { birthday } returns expectedBirthday
        }
        every { personViewModel.getPersons() } returns MutableStateFlow(listOf(person))

        // When
        launchActivity<MainActivity>()

        // Then
        onView(
            withRecyclerView(R.id.personsRecyclerView).atPositionOnView(
                0,
                R.id.birthdayTextView
            )
        ) matchesWithText expectedBirthday
    }

    @Test
    fun personsRecyclerViewAtFirstPositionOnViewPersonFullNameTextViewShouldMatchesWithExpectedColorGivenGetPersonsOnPersonViewModelReturnsAListOfPersonsWithFullName() {
        // Given
        val fullName = "${Random.nextInt()}"
        val person = mockk<UIPerson>(relaxed = true) {
            every { this@mockk.fullName } returns fullName
        }
        every { personViewModel.getPersons() } returns MutableStateFlow(listOf(person))

        // When
        launchActivity<MainActivity>()

        // Then
        val expectedColor = R.color.black
        onView(
            withRecyclerView(R.id.personsRecyclerView).atPositionOnView(
                0,
                R.id.personFullNameTextView
            )
        ) matchesWithTextColor expectedColor
    }

    @Test
    fun personsRecyclerViewAtFirstPositionOnViewAcronymTextViewShouldMatchesWithExpectedColorGivenGetPersonsOnPersonViewModelReturnsAListOfPersonsWithAcronym() {
        // Given
        val acronym = "${Random.nextInt()}"
        val person = mockk<UIPerson>(relaxed = true) {
            every { this@mockk.acronym } returns acronym
        }
        every { personViewModel.getPersons() } returns MutableStateFlow(listOf(person))

        // When
        launchActivity<MainActivity>()

        // Then
        val expectedColor = R.color.black
        onView(
            withRecyclerView(R.id.personsRecyclerView).atPositionOnView(
                0,
                R.id.acronymTextView
            )
        ) matchesWithTextColor expectedColor
    }

    @Test
    fun personsRecyclerViewAtFirstPositionOnViewBirthdayTextViewShouldMatchesWithExpectedColorGivenGetPersonsOnPersonViewModelReturnsAListOfPersonsWithBirthday() {
        // Given
        val birthday = "${Random.nextInt()}"
        val person = mockk<UIPerson>(relaxed = true) {
            every { this@mockk.birthday } returns birthday
        }
        every { personViewModel.getPersons() } returns MutableStateFlow(listOf(person))

        // When
        launchActivity<MainActivity>()

        // Then
        val expectedColor = R.color.black
        onView(
            withRecyclerView(R.id.personsRecyclerView).atPositionOnView(
                0,
                R.id.birthdayTextView
            )
        ) matchesWithTextColor expectedColor
    }
}