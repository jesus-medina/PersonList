package com.mupper.personlist.presentation.entity

import java.lang.IllegalArgumentException

data class UIPerson(
    val id: String,
    val firstName: String,
    val lastName: String,
    val birthday: String,
) {
    init {
        if (firstName.isEmpty()) throw IllegalArgumentException()
        if (lastName.isEmpty()) throw IllegalArgumentException()
    }

    val fullName: String get() = "$firstName $lastName"
    val acronym: String get() = "${firstName.first()}${lastName.first()}"
}