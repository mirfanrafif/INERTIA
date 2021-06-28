package com.inertia.ui.laporanmu

import androidx.lifecycle.ViewModel
import com.inertia.core.repository.bencana.IBencanaRepository
import com.inertia.core.repository.user.IUserRepository
import javax.inject.Inject

class LaporanmuViewModel @Inject constructor (
    private val bencanaRepository: IBencanaRepository,
    private val userRepository: IUserRepository
): ViewModel() {
    fun getUser() = userRepository.getUser()

    fun getBencanaByNomorWa(nomorWa: String) = bencanaRepository.getLaporanByNomorWa(nomorWa)
}