package com.inertia.core

import android.content.Context
import com.inertia.core.repository.bencana.IBencanaRepository
import com.inertia.core.repository.cuaca.ICuacaRepository
import com.inertia.core.repository.modules.BencanaModules
import com.inertia.core.repository.modules.CuacaModule
import com.inertia.core.repository.modules.UserModules
import com.inertia.core.repository.user.IUserRepository
import com.inertia.ui.detail.DetailReportActivity
import com.inertia.ui.form.FormActivity
import com.inertia.ui.home.HomeFragment
import com.inertia.ui.laporanmu.LaporanmuActivity
import com.inertia.ui.login.LoginActivity
import com.inertia.ui.main.MainActivity
import com.inertia.ui.profile.ProfileFragment
import com.inertia.ui.register.RegisterActivity
import com.inertia.ui.terdampak.TerdampakActivity
import com.inertia.ui.verification.VerificationActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    BencanaModules::class,
    UserModules::class,
    CuacaModule::class
])
@Singleton
interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideBencanaRepository(): IBencanaRepository
    fun provideUserRepository(): IUserRepository
    fun provideCuacaRepository(): ICuacaRepository

}