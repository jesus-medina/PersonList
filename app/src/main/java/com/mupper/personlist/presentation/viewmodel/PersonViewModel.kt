package com.mupper.personlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mupper.personlist.domain.entity.DomainPerson
import com.mupper.personlist.domain.repository.PersonRepository
import com.mupper.personlist.domain.usecase.GetPersonsUseCase
import com.mupper.personlist.domain.usecase.RetrievePersonsUseCase
import com.mupper.personlist.presentation.entity.UIPerson
import com.mupper.personlist.presentation.mapper.DomainPersonToUIPersonMapper
import com.mupper.personlist.utils.ListMapperImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface PersonViewModel {
    suspend fun retrievePersons()
    suspend fun getPersons(): StateFlow<List<UIPerson>>
}

@HiltViewModel
class PersonViewModelImpl @Inject constructor(
    private val getPersonsUseCase: GetPersonsUseCase,
    private val retrievePersonsUseCase: RetrievePersonsUseCase,
    private val domainPersonToUIPersonListMapper: ListMapperImpl<DomainPerson, UIPerson>,
) : ViewModel(), PersonViewModel {
    override suspend fun retrievePersons() {
        retrievePersonsUseCase()
    }

    override suspend fun getPersons(): StateFlow<List<UIPerson>> =
        getPersonsUseCase()
            .map(domainPersonToUIPersonListMapper::map)
            .stateIn(viewModelScope)
}