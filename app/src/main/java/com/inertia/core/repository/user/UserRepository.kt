package com.inertia.core.repository.user

import com.inertia.core.datasource.local.UserLocalDataSource
import com.inertia.core.datasource.local.entity.UserEntity
import com.inertia.core.datasource.remote.UserRemoteDataSource
import com.inertia.core.datasource.remote.request.RegisterRequest
import javax.inject.Inject

class UserRepository @Inject constructor(
    val local: UserLocalDataSource,
    val remote: UserRemoteDataSource
) : IUserRepository {
    override fun getUser(): UserEntity = local.getUser()

    override fun login(phoneNumber: String) = remote.login(phoneNumber)

    override fun register(request: RegisterRequest) = remote.register(request)

    override fun setUser(userEntity: UserEntity?) {
        local.setUser(userEntity)
    }
}