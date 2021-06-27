package com.inertia.ui.profile

import androidx.lifecycle.ViewModel
import com.inertia.data.datasource.local.entity.UserEntity
import com.inertia.data.repository.user.IUserRepository
import com.inertia.data.repository.user.UserRepository

class ProfileViewModel(private val userRepository: IUserRepository): ViewModel() {
    fun getUser() = userRepository.getUser()

    fun logout() {
        val userEntity = UserEntity()
        userRepository.setUser(userEntity)
    }
}