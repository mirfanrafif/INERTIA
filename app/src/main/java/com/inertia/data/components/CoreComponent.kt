package com.inertia.data.components

import android.content.Context
import com.inertia.data.repository.modules.BencanaModules
import com.inertia.data.repository.modules.CuacaModule
import com.inertia.data.repository.modules.UserModules
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

    fun inject(activity: MainActivity)

    fun inject(activity: DetailReportActivity)

    fun inject(activity: FormActivity)

    fun inject(fragment: HomeFragment)

    fun inject(activity: LaporanmuActivity)

    fun inject(activity: LoginActivity)

    fun inject(fragment: ProfileFragment)

    fun inject(registerActivity: RegisterActivity)

    fun inject(terdampakActivity: TerdampakActivity)

    fun inject(verificationActivity: VerificationActivity)
}