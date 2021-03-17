package com.mupper.personlist.utils

interface Mapper<in I, out O> {
    fun map(input: I): O
}