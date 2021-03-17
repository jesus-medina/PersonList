package com.mupper.personlist.data.remote

interface PersonRemoteDataSource {
    fun getPersons(): List<RemotePerson>
}