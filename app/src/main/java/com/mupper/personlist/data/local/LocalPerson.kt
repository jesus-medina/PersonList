package com.mupper.personlist.data.local

import java.util.Date

data class LocalPerson(
    val id: String,
    val firstName: String,
    val lastName: String,
    val birthday: Date
)