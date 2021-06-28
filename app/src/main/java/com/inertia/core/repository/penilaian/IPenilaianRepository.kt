package com.inertia.core.repository.penilaian

import com.inertia.core.datasource.remote.request.StoreFormPenilaianRequest

interface IPenilaianRepository {
    fun storeFormPenilaian(request: StoreFormPenilaianRequest, callback: storeFormPenilaianCallback)

    interface storeFormPenilaianCallback {
        fun onStoreFormPenilaianSuccessCallback(status: Boolean?)
    }
}