package com.inertia.data.datasource.local

import com.inertia.data.datasource.local.entity.UserEntity
import com.inertia.data.datasource.local.preference.IUserPreferences
import com.inertia.data.datasource.local.preference.UserPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor(private val preferences: IUserPreferences) {
    fun getUser() = preferences.getUser()

    fun setUser(userEntity: UserEntity?) = preferences.setUser(userEntity)
}