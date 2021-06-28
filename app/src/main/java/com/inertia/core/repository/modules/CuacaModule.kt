package com.inertia.core.repository.modules

import com.inertia.core.datasource.remote.CuacaRemoteDataSource
import com.inertia.core.datasource.remote.api.CuacaService
import com.inertia.core.datasource.remote.RemoteModule
import com.inertia.core.repository.cuaca.CuacaRepository
import com.inertia.core.repository.cuaca.ICuacaRepository
import dagger.Module
import dagger.Provides

@Module(includes = [
    RemoteModule::class
])
class CuacaModule {
    @Provides
    fun provideCuacaRemote(cuacaService: CuacaService): CuacaRemoteDataSource =
        CuacaRemoteDataSource(cuacaService)

    @Provides
    fun provideCuacaRepository(cuacaRemoteDataSource: CuacaRemoteDataSource): ICuacaRepository =
        CuacaRepository(cuacaRemoteDataSource)
}