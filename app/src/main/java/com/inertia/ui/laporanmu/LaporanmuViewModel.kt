package com.inertia.ui.laporanmu

import androidx.lifecycle.ViewModel
import com.inertia.data.repository.bencana.BencanaRepository
import com.inertia.data.repository.bencana.IBencanaRepository
import com.inertia.data.repository.user.IUserRepository
import com.inertia.data.repository.user.UserRepository
import javax.inject.Inject

class LaporanmuViewModel @Inject constructor (
    private val bencanaRepository: IBencanaRepository,
    private val userRepository: IUserRepository
): ViewModel() {
    fun getUser() = userRepository.getUser()

    fun getBencanaByNomorWa(nomorWa: String) = bencanaRepository.getLaporanByNomorWa(nomorWa)
}