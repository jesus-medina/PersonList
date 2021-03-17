package com.mupper.personlist.presentation.entity

data class UIPerson(
    val id: String,
    val firstName: String,
    val lastName: String,
    val birthday: String,
) {
    val fullName: String get() = TODO()
    val acronym: String get() = TODO()
}