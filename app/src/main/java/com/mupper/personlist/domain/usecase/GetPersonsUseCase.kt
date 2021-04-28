package com.mupper.personlist.domain.usecase

import com.mupper.personlist.domain.entity.DomainPerson
import com.mupper.personlist.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPersonsUseCase {
    operator fun invoke(): Flow<List<DomainPerson>>
}

class GetPersonsUseCaseImpl @Inject constructor(
    private val personRepository: PersonRepository
) : GetPersonsUseCase {
    override fun invoke(): Flow<List<DomainPerson>> = personRepository.getPersons()
}