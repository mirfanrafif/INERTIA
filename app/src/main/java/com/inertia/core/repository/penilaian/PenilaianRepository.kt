package com.inertia.core.repository.penilaian

import com.inertia.core.datasource.remote.PenilaianRemoteDataSource
import com.inertia.core.datasource.remote.request.StoreFormPenilaianRequest
import com.inertia.utils.AppExecutor

class PenilaianRepository(val remote: PenilaianRemoteDataSource, private val appExecutor: AppExecutor): IPenilaianRepository {

    companion object {
        @Volatile
        private var instance: PenilaianRepository? = null

        fun getInstance(remote: PenilaianRemoteDataSource, appExecutor: AppExecutor): PenilaianRepository =
            instance ?: synchronized(this) {
                instance ?: PenilaianRepository(remote, appExecutor).apply {
                    instance = this
                }
            }
    }


    override fun storeFormPenilaian(
        request: StoreFormPenilaianRequest,
        callback: IPenilaianRepository.storeFormPenilaianCallback
    ) = remote.store(request, callback)

}