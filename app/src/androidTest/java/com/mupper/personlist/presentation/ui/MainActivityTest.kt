package com.mupper.personlist.presentation.ui

import android.content.Context
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
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
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    var personViewModel: PersonViewModelImpl = mockk(relaxed = true) {
        coEvery { retrievePersons() } just runs
        coEvery { getPersons() } returns MutableStateFlow(emptyList())
    }

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
        coEvery { personViewModel.getPersons() } returns MutableStateFlow(listOf(person))

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
        coEvery { personViewModel.getPersons() } returns MutableStateFlow(listOf(person))

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
        coEvery { personViewModel.getPersons() } returns MutableStateFlow(listOf(person))

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
        coEvery { personViewModel.getPersons() } returns MutableStateFlow(listOf(person))

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
        coEvery { personViewModel.getPersons() } returns MutableStateFlow(listOf(person))

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
        coEvery { personViewModel.getPersons() } returns MutableStateFlow(listOf(person))

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

    @Test
    fun swipeDownOnPersonsRecyclerViewShouldCallRetrievePersonsOnPersonViewModelGiveActivityIsLaunched() {
        // Given
        launchActivity<MainActivity>()

        // When
        onView(withId(R.id.personsRecyclerView)).perform(swipeDown())

        // Then
        coVerify { personViewModel.retrievePersons() }
    }

    @Test
    fun personsSwipeToRefreshShouldNotBeRefreshingGivenSwipeDownOnPersonsRecyclerView() {
        // Given
        launchActivity<MainActivity>()

        // When
        onView(withId(R.id.personsRecyclerView)).perform(swipeDown())

        // Then
        onView(withId(R.id.personsSwipeToRefresh)).check { view, _ ->
            val swipeRefreshLayout = view as SwipeRefreshLayout
            assertThat(swipeRefreshLayout.isRefreshing, `is`(false))
        }
    }

    @Test
    fun retrievePersonsShouldBeCalledGivenActivityIsLaunched() {
        // When
        launchActivity<MainActivity>()

        // Then
        coVerify { personViewModel.retrievePersons() }
    }
}