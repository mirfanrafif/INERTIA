package com.inertia.core.datasource.local.preference

import com.inertia.core.datasource.local.entity.UserEntity
import javax.inject.Singleton

@Singleton
interface IUserPreferences {
    fun setUser(user: UserEntity?)
    fun getUser(): UserEntity
}