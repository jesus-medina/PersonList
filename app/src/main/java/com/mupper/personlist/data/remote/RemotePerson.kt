package com.mupper.personlist.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class RemotePerson(
    val id: String?,
    val firstName: String?,
    val lastName: String?,
    val birthday: String?
)