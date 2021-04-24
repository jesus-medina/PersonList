package com.mupper.personlist.domain.mapper

import com.mupper.personlist.data.local.LocalPerson
import com.mupper.personlist.data.remote.RemotePerson
import com.mupper.personlist.exception.RemoteMappingException
import com.mupper.personlist.utils.Mapper
import java.text.DateFormat
import javax.inject.Inject

interface RemotePersonToLocalPersonMapper : Mapper<RemotePerson, LocalPerson>

class RemotePersonToLocalPersonMapperImpl @Inject constructor(
    private val dateFormat: DateFormat,
) : RemotePersonToLocalPersonMapper {
    override fun map(input: RemotePerson): LocalPerson = with(input) {
        val id = id ?: throw RemoteMappingException()
        val firstName = firstName ?: throw RemoteMappingException()
        val lastName = lastName ?: throw RemoteMappingException()
        val birthdaySource = birthday ?: throw RemoteMappingException()
        val birthday = dateFormat.parse(birthdaySource) ?: throw RemoteMappingException()

        LocalPerson(id.toLong(), firstName, lastName, "birthday")
    }
}