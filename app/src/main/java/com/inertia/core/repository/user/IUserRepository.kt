package com.inertia.core.repository.user

import androidx.lifecycle.LiveData
import com.inertia.core.datasource.local.entity.UserEntity
import com.inertia.core.datasource.remote.request.RegisterRequest
import com.inertia.core.datasource.remote.response.ApiResponse
import com.inertia.core.datasource.remote.response.LoginResponse
import com.inertia.core.datasource.remote.response.RegisterResponse

interface IUserRepository {
    fun getUser(): UserEntity

    fun login(phoneNumber: String): LiveData<ApiResponse<LoginResponse>>

    fun register(request: RegisterRequest): LiveData<ApiResponse<RegisterResponse>>

    fun setUser(userEntity: UserEntity?)
}