package com.inertia.core.repository.bencana

import androidx.lifecycle.LiveData
import com.inertia.core.datasource.local.entity.BencanaEntity
import com.inertia.core.datasource.remote.request.BencanaRequest
import com.inertia.core.datasource.remote.response.ApiResponse
import com.inertia.core.datasource.remote.response.LaporResponse
import com.mirfanrafif.kicksfilm.vo.Resource

interface IBencanaRepository {
    fun getAllBencana(): LiveData<Resource<List<BencanaEntity>>>

    fun createLaporan(request: BencanaRequest): LiveData<ApiResponse<LaporResponse>>

    fun getLaporanByNomorWa(nomorWa: String): LiveData<Resource<List<BencanaEntity>>>

    fun updateBencana(idAduan: String, uriDonasi: String): LiveData<BencanaEntity>
}