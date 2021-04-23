package com.mupper.personlist.domain.usecase

import com.mupper.personlist.domain.repository.PersonRepository
import javax.inject.Inject

interface RetrievePersonsUseCase {
    suspend operator fun invoke()
}

class RetrievePersonsUseCaseImpl @Inject constructor(
    private val personRepository: PersonRepository
) : RetrievePersonsUseCase {
    override suspend fun invoke() {
        personRepository.retrievePersons()
    }
}