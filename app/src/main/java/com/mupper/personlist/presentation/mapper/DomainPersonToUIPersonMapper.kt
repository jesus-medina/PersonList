package com.mupper.personlist.presentation.mapper

import com.mupper.personlist.domain.entity.DomainPerson
import com.mupper.personlist.presentation.entity.UIPerson
import com.mupper.personlist.utils.Mapper

interface DomainPersonToUIPersonMapper : Mapper<DomainPerson, UIPerson>