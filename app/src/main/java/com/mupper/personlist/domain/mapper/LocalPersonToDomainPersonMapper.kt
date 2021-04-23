package com.mupper.personlist.domain.mapper

import com.mupper.personlist.data.local.LocalPerson
import com.mupper.personlist.data.remote.RemotePerson
import com.mupper.personlist.domain.entity.DomainPerson
import com.mupper.personlist.utils.Mapper
import javax.inject.Inject

interface LocalPersonToDomainPersonMapper : Mapper<LocalPerson, DomainPerson>

class LocalPersonToDomainPersonMapperImpl @Inject constructor() : LocalPersonToDomainPersonMapper {
    override fun map(input: LocalPerson): DomainPerson {
        TODO("Not yet implemented")
    }
}