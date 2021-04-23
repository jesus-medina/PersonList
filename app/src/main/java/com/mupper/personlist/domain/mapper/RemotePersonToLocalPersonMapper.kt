package com.mupper.personlist.domain.mapper

import com.mupper.personlist.data.local.LocalPerson
import com.mupper.personlist.data.remote.RemotePerson
import com.mupper.personlist.utils.Mapper
import javax.inject.Inject

interface RemotePersonToLocalPersonMapper : Mapper<RemotePerson, LocalPerson>

class RemotePersonToLocalPersonMapperImpl @Inject constructor() : RemotePersonToLocalPersonMapper {
    override fun map(input: RemotePerson): LocalPerson {
        TODO("Not yet implemented")
    }
}