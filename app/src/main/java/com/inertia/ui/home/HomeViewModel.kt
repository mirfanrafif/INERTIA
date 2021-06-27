package com.inertia.ui.home

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.inertia.data.repository.bencana.BencanaRepository
import com.inertia.data.repository.bencana.IBencanaRepository
import com.inertia.data.repository.cuaca.CuacaRepository
import com.inertia.data.repository.user.IUserRepository
import com.inertia.data.repository.user.UserRepository
import com.inertia.utils.LocationProvider
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val bencanaRepository: IBencanaRepository,
    private val userRepository: IUserRepository,
    private val cuacaRepository: CuacaRepository
): ViewModel() {
    fun getUser() = userRepository.getUser()

    fun getLocation(activity: Activity) = LocationProvider.getLocation(activity)

    fun getAllBencana() = bencanaRepository.getAllBencana()

    fun getCuaca(latitude: Double, longitude: Double) = cuacaRepository.getCuaca(latitude, longitude)
}