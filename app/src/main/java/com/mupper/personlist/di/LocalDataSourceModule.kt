package com.mupper.personlist.di

import android.content.Context
import com.mupper.personlist.PersonDatabase
import com.mupper.personlist.PersonQueries
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {
    @Provides
    fun providesSqliteDriver(
        @ApplicationContext context: Context,
    ): SqlDriver =
        AndroidSqliteDriver(PersonDatabase.Schema, context, "personlist.db")

    @Provides
    fun providesDatabase(sqlDriver: SqlDriver): PersonDatabase =
        PersonDatabase(sqlDriver)

    @Provides
    fun providesQueries(personDatabase: PersonDatabase): PersonQueries =
        personDatabase.personQueries
}