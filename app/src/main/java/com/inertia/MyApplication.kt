package com.inertia

import android.app.Application
import com.inertia.core.CoreComponent
import com.inertia.core.DaggerCoreComponent

open class MyApplication: Application() {
    val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}