package com.inertia.core.datasource.local

import com.inertia.core.datasource.local.dao.BencanaDao
import com.inertia.core.datasource.local.entity.BencanaEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BencanaLocalDataSource @Inject constructor(private val bencanaDao: BencanaDao) {

    companion object {
        @Volatile
        private var instance: BencanaLocalDataSource? = null

        fun getInstance(bencanaDao: BencanaDao): BencanaLocalDataSource {
            return instance ?: synchronized(this) {
                instance ?: BencanaLocalDataSource(bencanaDao).apply {
                    instance = this
                }
            }
        }
    }

    fun getAllBencana() = bencanaDao.getAllBencana()

    fun getBencanaByNomorWa(nomorWa: String) = bencanaDao.getBencanaByNomorWa(nomorWa)

    fun insertBencana(list: List<BencanaEntity>) = bencanaDao.insertBencana(list)

    fun getBencanaById(id: String) = bencanaDao.getBencanaById(id)

    fun updateBencanaUri(idAduan: String, uriDonasi: String) = bencanaDao.updateBencana(idAduan, uriDonasi)
}