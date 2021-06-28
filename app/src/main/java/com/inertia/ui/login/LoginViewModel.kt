package com.inertia.ui.login

import androidx.lifecycle.ViewModel
import com.inertia.core.repository.user.IUserRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor (val userRepository: IUserRepository): ViewModel() {
    fun login(phoneNumber: String) = userRepository.login(phoneNumber)
}