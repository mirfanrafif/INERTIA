package com.inertia.core.datasource.local.preference

import android.content.Context
import androidx.core.content.edit
import com.inertia.core.datasource.local.entity.UserEntity
import javax.inject.Singleton

@Singleton
class UserPreferences(context: Context): IUserPreferences {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val NAME = "name"
        private const val PHONE_NUMBER = "phone_number"
        private const val ALAMAT = "alamat"
        private const val JENIS_KELAMIN = "jenis_kelamin"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun setUser(user: UserEntity?) {
        preferences.edit {
            putString(NAME, user?.nama)
            putString(PHONE_NUMBER, user?.nomorWa)
            putString(ALAMAT, user?.alamat)
            putString(JENIS_KELAMIN, user?.jenisKelamin)
        }
    }

    override fun getUser(): UserEntity {
        val user = UserEntity()
        user.nama = preferences.getString(NAME, null)
        user.nomorWa = preferences.getString(PHONE_NUMBER, null)
        user.alamat = preferences.getString(ALAMAT, null)
        user.jenisKelamin = preferences.getString(JENIS_KELAMIN, null)
        return user
    }
}