package com.inertia.ui.main

import androidx.lifecycle.ViewModel
import com.inertia.data.repository.user.IUserRepository
import com.inertia.data.repository.user.UserRepository
import javax.inject.Inject

class MainViewModel @Inject constructor (private val userRepository: IUserRepository): ViewModel() {
    fun getUser() = userRepository.getUser()
}