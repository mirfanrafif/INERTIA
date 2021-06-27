package com.inertia.data.repository.modules

import com.inertia.data.datasource.local.BencanaLocalDataSource
import com.inertia.data.datasource.local.database.DatabaseModule
import com.inertia.data.datasource.remote.BencanaRemoteDataSource
import com.inertia.data.datasource.remote.api.RemoteModule
import com.inertia.data.repository.bencana.BencanaRepository
import com.inertia.data.repository.bencana.IBencanaRepository
import com.inertia.utils.AppExecutor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [
    DatabaseModule::class,
    RemoteModule::class
])
class BencanaModules {
    @Provides
    fun provideAppExecutor(): AppExecutor = AppExecutor()

    @Provides
    fun provideBencanaRepository(
        bencanaLocalDataSource: BencanaLocalDataSource,
        bencanaRemoteDataSource: BencanaRemoteDataSource,
        appExecutor: AppExecutor)
    : IBencanaRepository = BencanaRepository(
        bencanaRemoteDataSource,
        bencanaLocalDataSource,
        appExecutor
    )
}