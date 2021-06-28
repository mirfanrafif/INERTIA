package com.inertia.ui.verification

import androidx.lifecycle.ViewModel
import com.inertia.core.datasource.local.entity.UserEntity
import com.inertia.core.repository.user.IUserRepository
import javax.inject.Inject

class VerificationViewModel @Inject constructor (private val userRepository: IUserRepository)
    : ViewModel() {
    fun saveUser(userEntity: UserEntity?) {
        userRepository.setUser(userEntity)
    }
}