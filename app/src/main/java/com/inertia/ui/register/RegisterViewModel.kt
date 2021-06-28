package com.inertia.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.inertia.core.datasource.remote.request.RegisterRequest
import com.inertia.core.datasource.remote.response.ApiResponse
import com.inertia.core.datasource.remote.response.RegisterResponse
import com.inertia.core.repository.user.IUserRepository
import javax.inject.Inject

class RegisterViewModel @Inject constructor (val userRepository: IUserRepository): ViewModel() {
    fun register(request: RegisterRequest): LiveData<ApiResponse<RegisterResponse>> =
        userRepository.register(request)
}