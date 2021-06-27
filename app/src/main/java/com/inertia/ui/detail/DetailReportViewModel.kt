package com.inertia.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inertia.data.datasource.local.entity.BencanaEntity
import com.inertia.data.repository.bencana.BencanaRepository
import com.inertia.data.repository.bencana.IBencanaRepository
import com.inertia.data.repository.user.IUserRepository
import com.inertia.data.repository.user.UserRepository
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