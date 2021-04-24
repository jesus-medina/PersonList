package com.mupper.personlist.domain.mapper

import com.mupper.personlist.data.local.LocalPerson
import com.mupper.personlist.domain.entity.DomainPerson
import com.mupper.personlist.exception.LocalMappingException
import com.mupper.personlist.utils.Mapper
import java.text.DateFormat
import javax.inject.Inject

interface LocalPersonToDomainPersonMapper : Mapper<LocalPerson, DomainPerson>

class LocalPersonToDomainPersonMapperImpl @Inject constructor(
    private val dateFormat: DateFormat
) : LocalPersonToDomainPersonMapper {
    override fun map(input: LocalPerson): DomainPerson = with(input) {
        val birthday = dateFormat.parse(birthday) ?: throw LocalMappingException()

        DomainPerson("$id", firstName, lastName, birthday)
    }
}