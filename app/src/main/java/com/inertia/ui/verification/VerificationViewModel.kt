package com.inertia.ui.verification

import androidx.lifecycle.ViewModel
import com.inertia.data.datasource.local.entity.UserEntity
import com.inertia.data.repository.user.IUserRepository
import com.inertia.data.repository.user.UserRepository
import javax.inject.Inject

class VerificationViewModel @Inject constructor (private val userRepository: IUserRepository)
    : ViewModel() {
    fun saveUser(userEntity: UserEntity?) {
        userRepository.setUser(userEntity)
    }
}