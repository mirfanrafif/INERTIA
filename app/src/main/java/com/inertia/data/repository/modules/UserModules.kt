package com.inertia.data.repository.modules

import android.content.Context
import com.inertia.data.datasource.local.UserLocalDataSource
import com.inertia.data.datasource.local.preference.IUserPreferences
import com.inertia.data.datasource.local.preference.UserPreferences
import com.inertia.data.datasource.remote.UserRemoteDataSource
import com.inertia.data.datasource.remote.api.RemoteModule
import com.inertia.data.datasource.remote.api.UserService
import com.inertia.data.repository.user.IUserRepository
import com.inertia.data.repository.user.UserRepository
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