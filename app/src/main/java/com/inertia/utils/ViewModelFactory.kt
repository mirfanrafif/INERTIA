package com.inertia.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inertia.data.repository.bencana.BencanaRepository
import com.inertia.data.repository.bencana.IBencanaRepository
import com.inertia.data.repository.cuaca.CuacaRepository
import com.inertia.data.repository.user.IUserRepository
import com.inertia.data.repository.user.UserRepository
import com.inertia.ui.detail.DetailReportViewModel
import com.inertia.ui.form.FormViewModel
import com.inertia.ui.home.HomeViewModel
import com.inertia.ui.laporanmu.LaporanmuViewModel
import com.inertia.ui.login.LoginViewModel
import com.inertia.ui.main.MainViewModel
import com.inertia.ui.profile.ProfileViewModel
import com.inertia.ui.register.RegisterViewModel
import com.inertia.ui.verification.VerificationViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor (
    private val bencanaRepository: IBencanaRepository,
    private val userRepository: IUserRepository,
    private val cuacaRepository: CuacaRepository
    ) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(userRepository) as T
            modelClass.isAssignableFrom(VerificationViewModel::class.java) -> VerificationViewModel(userRepository) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(userRepository) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(userRepository) as T
            modelClass.isAssignableFrom(FormViewModel::class.java) -> FormViewModel(bencanaRepository, userRepository) as T
            modelClass.isAssignableFrom(LaporanmuViewModel::class.java) -> LaporanmuViewModel(bencanaRepository, userRepository) as T
            modelClass.isAssignableFrom(DetailReportViewModel::class.java) -> DetailReportViewModel(bencanaRepository, userRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(bencanaRepository, userRepository, cuacaRepository) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(userRepository) as T
            else -> throw Throwable("Unknown viewmodel class")
        }
    }
}