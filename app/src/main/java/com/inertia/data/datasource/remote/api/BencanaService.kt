package com.inertia.data.datasource.remote.api

import com.inertia.data.datasource.remote.response.BencanaResponse
import retrofit2.Call
import retrofit2.http.GET

interface BencanaService {
    @GET("/report/view")
    fun getAllBencana(): Call<BencanaResponse>
}