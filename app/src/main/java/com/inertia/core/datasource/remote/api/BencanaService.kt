package com.inertia.core.datasource.remote.api

import com.inertia.core.datasource.remote.response.BencanaResponse
import com.inertia.core.datasource.remote.response.LaporResponse
import com.inertia.core.datasource.remote.response.UpdateBencanaResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface BencanaService {
    @GET("report/show")
    fun getAllBencana(): Call<BencanaResponse>

    @GET("report/get/{nomor_wa}")
    fun getBencanaByNomorWa(@Path("nomor_wa") nomorWa: String): Call<BencanaResponse>

    @Multipart
    @POST("report/add")
    fun createLaporan(
        @Part filePart: MultipartBody.Part,
        @Query("judul") judul: String,
        @Query("kronologi") kronologi: String,
        @Query("nomor_wa") nomor_wa: String,
        @Query("waktu_bencana") waktu_bencana: String,
        @Query("lat_long") lat_long: String
    ): Call<LaporResponse>

    @POST("report/update")
    fun editDonasiUri(
        @Query("id_aduan") idAduan: String,
        @Query("url") donasiUri: String):
            Call<UpdateBencanaResponse>
}