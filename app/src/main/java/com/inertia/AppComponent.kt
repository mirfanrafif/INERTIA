package com.inertia

import com.inertia.core.CoreComponent
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
import com.inertia.utils.AppScope
import dagger.Component

@AppScope
@Component(dependencies = [
    CoreComponent::class
])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
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