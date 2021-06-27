package com.inertia

import android.app.Application
import com.inertia.data.components.CoreComponent
import com.inertia.data.components.DaggerCoreComponent

open class MyApplication: Application() {
    val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }
}