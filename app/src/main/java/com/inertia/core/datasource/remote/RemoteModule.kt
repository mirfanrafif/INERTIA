package com.inertia.core.datasource.remote

import com.inertia.core.datasource.remote.api.BencanaService
import com.inertia.core.datasource.remote.api.CuacaService
import com.inertia.core.datasource.remote.api.UserService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class RemoteModule {
    @Provides
    fun provideHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    @Provides
    fun provideInertiaService(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl("http://35.224.208.225/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun getBencanaService(retrofit: Retrofit): BencanaService = retrofit.create(BencanaService::class.java)

    @Provides
    fun getUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

    @Provides
    fun getCuacaService(retrofit: Retrofit): CuacaService = retrofit.create(CuacaService::class.java)
}