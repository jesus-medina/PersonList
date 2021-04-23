package com.mupper.personlist.presentation.mapper

import com.mupper.personlist.domain.entity.DomainPerson
import com.mupper.personlist.presentation.entity.UIPerson
import com.mupper.personlist.utils.Mapper
import javax.inject.Inject

interface DomainPersonToUIPersonMapper : Mapper<DomainPerson, UIPerson>

class DomainPersonToUIPersonMapperImpl @Inject constructor() : DomainPersonToUIPersonMapper {
    override fun map(input: DomainPerson): UIPerson {
        TODO("Not yet implemented")
    }
}