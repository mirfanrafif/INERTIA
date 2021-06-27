package com.inertia.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.inertia.data.datasource.remote.request.RegisterRequest
import com.inertia.data.datasource.remote.response.ApiResponse
import com.inertia.data.datasource.remote.response.RegisterResponse
import com.inertia.data.repository.user.IUserRepository
import com.inertia.data.repository.user.UserRepository
import javax.inject.Inject

class RegisterViewModel @Inject constructor (val userRepository: IUserRepository): ViewModel() {
    fun register(request: RegisterRequest): LiveData<ApiResponse<RegisterResponse>> =
        userRepository.register(request)
}