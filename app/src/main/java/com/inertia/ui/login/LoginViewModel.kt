package com.inertia.ui.login

import androidx.lifecycle.ViewModel
import com.inertia.data.repository.user.IUserRepository
import com.inertia.data.repository.user.UserRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor (val userRepository: IUserRepository): ViewModel() {
    fun login(phoneNumber: String) = userRepository.login(phoneNumber)
}