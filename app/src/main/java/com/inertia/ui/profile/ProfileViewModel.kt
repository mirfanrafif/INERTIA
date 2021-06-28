package com.inertia.ui.profile

import androidx.lifecycle.ViewModel
import com.inertia.core.datasource.local.entity.UserEntity
import com.inertia.core.repository.user.IUserRepository

class ProfileViewModel(private val userRepository: IUserRepository): ViewModel() {
    fun getUser() = userRepository.getUser()

    fun logout() {
        val userEntity = UserEntity()
        userRepository.setUser(userEntity)
    }
}