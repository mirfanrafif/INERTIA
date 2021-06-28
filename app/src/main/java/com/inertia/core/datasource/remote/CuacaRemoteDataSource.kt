package com.inertia.core.datasource.remote

import com.inertia.core.datasource.remote.api.CuacaService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CuacaRemoteDataSource @Inject constructor (private val service: CuacaService) {

    companion object {
        @Volatile
        private var instance: CuacaRemoteDataSource? = null

        fun getInstance(service: CuacaService): CuacaRemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: CuacaRemoteDataSource(service).apply {
                    instance = this
                }
            }
    }

    fun getCuaca(latitude: Double, longitude: Double) = service.getCuaca(latitude, longitude)
}