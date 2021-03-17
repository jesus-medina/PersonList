package com.mupper.personlist.domain.mapper

import com.mupper.personlist.data.local.LocalPerson
import com.mupper.personlist.data.remote.RemotePerson
import com.mupper.personlist.utils.Mapper

interface LocalPersonToDomainPersonMapper : Mapper<LocalPerson, RemotePerson>