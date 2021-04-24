package com.mupper.personlist.domain.mapper

import com.mupper.personlist.data.local.LocalPerson
import com.mupper.personlist.data.remote.RemotePerson
import com.mupper.personlist.domain.entity.DomainPerson
import com.mupper.personlist.utils.Mapper
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

interface LocalPersonToDomainPersonMapper : Mapper<LocalPerson, DomainPerson>

class LocalPersonToDomainPersonMapperImpl @Inject constructor() : LocalPersonToDomainPersonMapper {
    override fun map(input: LocalPerson): DomainPerson = with(input) {
        DomainPerson(id, firstName, lastName, birthday)
    }
}