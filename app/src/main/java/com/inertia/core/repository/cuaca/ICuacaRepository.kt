package com.inertia.core.repository.cuaca

import androidx.lifecycle.LiveData
import com.inertia.core.datasource.local.entity.CuacaEntity

interface ICuacaRepository {
    fun getCuaca(latitude: Double, longitude: Double): LiveData<CuacaEntity>
}