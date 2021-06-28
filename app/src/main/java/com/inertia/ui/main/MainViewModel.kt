package com.inertia.ui.main

import androidx.lifecycle.ViewModel
import com.inertia.core.repository.user.IUserRepository
import javax.inject.Inject

class MainViewModel @Inject constructor (private val userRepository: IUserRepository): ViewModel() {
    fun getUser() = userRepository.getUser()
}