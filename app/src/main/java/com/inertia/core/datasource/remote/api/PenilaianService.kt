package com.inertia.core.datasource.remote.api

import com.inertia.core.datasource.remote.response.PenilaianResponse
import com.inertia.core.datasource.remote.response.StoreFormPenilaianResponse
import com.inertia.core.response.JenisBencanaResponse
import com.inertia.core.response.SkalaResponse
import com.inertia.core.response.SubSektorResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PenilaianService {
    @GET("/skalas")
    fun getSkalas(): Call<List<SkalaResponse>>

    @GET("/jenisbencanas")
    fun getJenisBencanas(): Call<List<JenisBencanaResponse>>

    @GET("/subsektors")
    fun getSubSektors(): Call<List<SubSektorResponse>>

    @POST("/storeformpenilaian")
    fun storeFormPenilaian(
        @Query("nomorWa") username: String?,
        @Query("nmBencana") nmBencana: String?,
        @Query("idSub") idSub: Int?,
        @Query("name") name: String?,
        @Query("alamat") alamat: String?,
        @Query("provinsi") provinsi: String?,
        @Query("kota") kota: String?,
        @Query("tanggal") tanggal: String?,
        @Query("penilaian") penilaian: String?,
    ): Call<StoreFormPenilaianResponse>

    @GET("/multimoora4")
    fun getPenilaian(
        @Query("idKasus") idKasus: String?,
    ): Call<PenilaianResponse>
}