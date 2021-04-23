package com.mupper.personlist.domain.usecase

import com.mupper.personlist.domain.entity.DomainPerson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPersonsUseCase {
    operator fun invoke(): Flow<List<DomainPerson>>
}

class GetPersonsUseCaseImpl @Inject constructor() : GetPersonsUseCase {
    override fun invoke(): Flow<List<DomainPerson>> {
        TODO("Not yet implemented")
    }
}