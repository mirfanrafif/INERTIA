package com.inertia.ui.form

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.inertia.core.datasource.remote.request.BencanaRequest
import com.inertia.core.repository.bencana.IBencanaRepository
import com.inertia.core.repository.user.IUserRepository
import com.inertia.utils.LocationProvider

class FormViewModel(private val bencana: IBencanaRepository,
                    private val user: IUserRepository
): ViewModel() {
    fun createLaporan(request: BencanaRequest) = bencana.createLaporan(request)

    fun getUser() = user.getUser()

    fun getLocation(activity: Activity) = LocationProvider.getLocation(activity)
}