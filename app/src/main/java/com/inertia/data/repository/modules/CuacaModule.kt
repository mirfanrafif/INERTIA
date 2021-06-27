package com.inertia.data.repository.modules

import com.inertia.data.datasource.remote.CuacaRemoteDataSource
import com.inertia.data.datasource.remote.api.CuacaService
import com.inertia.data.datasource.remote.api.RemoteModule
import com.inertia.data.repository.cuaca.CuacaRepository
import com.inertia.data.repository.cuaca.ICuacaRepository
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