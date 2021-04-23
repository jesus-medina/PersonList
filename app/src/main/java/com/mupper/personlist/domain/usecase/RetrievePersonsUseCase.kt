package com.mupper.personlist.domain.usecase

import javax.inject.Inject

interface RetrievePersonsUseCase {
    suspend operator fun invoke()
}

class RetrievePersonsUseCaseImpl @Inject constructor() : RetrievePersonsUseCase {
    override suspend fun invoke() {
        TODO("Not yet implemented")
    }
}