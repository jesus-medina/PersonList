package com.mupper.personlist.domain.usecase

import com.mupper.personlist.domain.entity.DomainPerson
import kotlinx.coroutines.flow.Flow

interface GetPersonsUseCase {
    operator fun invoke(): Flow<List<DomainPerson>>
}