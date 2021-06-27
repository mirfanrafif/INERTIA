package com.inertia.data.datasource.local.preference

import com.inertia.data.datasource.local.entity.UserEntity
import dagger.Provides
import javax.inject.Singleton

@Singleton
interface IUserPreferences {
    fun setUser(user: UserEntity?)
    fun getUser(): UserEntity
}