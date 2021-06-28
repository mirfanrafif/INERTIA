package com.inertia.core.datasource.remote.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
class InertiaService {
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    private val retrofitPenilaian = Retrofit.Builder()
        .baseUrl("http://34.82.125.98:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getPenilaian(): PenilaianService = retrofitPenilaian.create(PenilaianService::class.java)

    fun getTerdampak(): TerdampakService = retrofitPenilaian.create(TerdampakService::class.java)
}