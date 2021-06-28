package com.inertia.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inertia.core.datasource.local.entity.BencanaEntity
import com.inertia.core.repository.bencana.IBencanaRepository
import com.inertia.core.repository.user.IUserRepository
import javax.inject.Inject

class DetailReportViewModel @Inject constructor (private val bencanaRepository: IBencanaRepository,
                                                 private val userRepository: IUserRepository
)
    : ViewModel() {
    fun getUser() = userRepository.getUser()

    private val mutableBencana = MutableLiveData<BencanaEntity>()

    fun getBencana(): LiveData<BencanaEntity> = mutableBencana

    fun setBencana(bencanaEntity: BencanaEntity?) {mutableBencana.postValue(bencanaEntity)}

    fun updateBencana(idAduan: String, url: String) = bencanaRepository.updateBencana(idAduan, url)
}