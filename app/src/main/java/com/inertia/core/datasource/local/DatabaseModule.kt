package com.inertia.core.datasource.local

import android.content.Context
import androidx.room.Room
import com.inertia.core.datasource.local.dao.BencanaDao
import com.inertia.core.datasource.local.database.InertiaDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): InertiaDatabase = Room.databaseBuilder(
        context.applicationContext,
        InertiaDatabase::class.java, "inertia")
        .build()

    @Provides
    fun bencanaDao(database: InertiaDatabase): BencanaDao = database.bencanaDao()
}