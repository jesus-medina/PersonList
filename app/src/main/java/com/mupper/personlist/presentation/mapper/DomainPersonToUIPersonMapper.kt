package com.mupper.personlist.presentation.mapper

import com.mupper.personlist.domain.entity.DomainPerson
import com.mupper.personlist.presentation.entity.UIPerson
import com.mupper.personlist.utils.Mapper
import java.text.DateFormat
import javax.inject.Inject

interface DomainPersonToUIPersonMapper : Mapper<DomainPerson, UIPerson>

class DomainPersonToUIPersonMapperImpl @Inject constructor(
    private val birthdayDateFormat: DateFormat
) : DomainPersonToUIPersonMapper {
    override fun map(input: DomainPerson): UIPerson = with(input) {
        val formattedBirthday = birthdayDateFormat.format(birthday)

        UIPerson(id, firstName, lastName, formattedBirthday)
    }
}