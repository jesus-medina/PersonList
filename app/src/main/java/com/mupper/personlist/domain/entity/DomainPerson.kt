package com.mupper.personlist.domain.entity

import java.util.Date

data class DomainPerson(
    val id: String,
    val firstName: String,
    val lastName: String,
    val birthday: Date,
)