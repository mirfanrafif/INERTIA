package com.inertia.core.repository.modules

import com.inertia.core.datasource.local.BencanaLocalDataSource
import com.inertia.core.datasource.local.DatabaseModule
import com.inertia.core.datasource.remote.BencanaRemoteDataSource
import com.inertia.core.datasource.remote.RemoteModule
import com.inertia.core.repository.bencana.BencanaRepository
import com.inertia.core.repository.bencana.IBencanaRepository
import com.inertia.utils.AppExecutor
import dagger.Module
import dagger.Provides

@Module(includes = [
    DatabaseModule::class,
    RemoteModule::class
])
class BencanaModules {
//    @Provides
//    fun provideAppExecutor(): AppExecutor = AppExecutor()

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