package com.inertia.core.repository.modules

import android.content.Context
import com.inertia.core.datasource.local.UserLocalDataSource
import com.inertia.core.datasource.local.preference.IUserPreferences
import com.inertia.core.datasource.local.preference.UserPreferences
import com.inertia.core.datasource.remote.UserRemoteDataSource
import com.inertia.core.datasource.remote.RemoteModule
import com.inertia.core.datasource.remote.api.UserService
import com.inertia.core.repository.user.IUserRepository
import com.inertia.core.repository.user.UserRepository
import dagger.Module
import dagger.Provides

@Module(includes = [
    RemoteModule::class
])
class UserModules {
    @Provides
    fun provideUserPreferences(context: Context): IUserPreferences = UserPreferences(context)

    @Provides
    fun provideUserRemoteDataSource(service: UserService): UserRemoteDataSource =
        UserRemoteDataSource(service)

    @Provides
    fun provideUserRepository(local: UserLocalDataSource,
                              remote: UserRemoteDataSource)
    : IUserRepository = UserRepository(local, remote)
}