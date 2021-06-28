package com.inertia.core.datasource.local

import com.inertia.core.datasource.local.entity.UserEntity
import com.inertia.core.datasource.local.preference.IUserPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor(private val preferences: IUserPreferences) {
    fun getUser() = preferences.getUser()

    fun setUser(userEntity: UserEntity?) = preferences.setUser(userEntity)
}