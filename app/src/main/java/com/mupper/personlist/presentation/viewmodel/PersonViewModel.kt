package com.mupper.personlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.mupper.personlist.presentation.entity.UIPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

interface PersonViewModel {
    fun retrievePersons()
    fun getPersons(): StateFlow<List<UIPerson>>
}

@HiltViewModel
class PersonViewModelImpl @Inject constructor() : ViewModel(), PersonViewModel {
    override fun retrievePersons() {
        TODO("Not yet implemented")
    }

    override fun getPersons(): StateFlow<List<UIPerson>> {
        TODO("Not yet implemented")
    }
}