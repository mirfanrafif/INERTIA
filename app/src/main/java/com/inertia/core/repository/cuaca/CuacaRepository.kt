package com.inertia.core.repository.cuaca

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.inertia.core.datasource.local.entity.CuacaEntity
import com.inertia.core.datasource.remote.CuacaRemoteDataSource
import com.inertia.core.datasource.remote.response.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CuacaRepository @Inject constructor (private val cuacaRemoteDataSource: CuacaRemoteDataSource): ICuacaRepository {

    companion object {
        @Volatile
        private var instance: CuacaRepository? = null

        fun getInstance(remote: CuacaRemoteDataSource): CuacaRepository =
            instance ?: synchronized(this) {
                instance ?: CuacaRepository(remote).apply {
                    instance = this
                }
            }
    }

    override fun getCuaca(latitude: Double, longitude: Double): LiveData<CuacaEntity> {
    val dataCuaca = MutableLiveData<CuacaEntity>()
    cuacaRemoteDataSource.getCuaca(latitude, longitude).enqueue(object: Callback<WeatherResponse>{
        override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
            if(response.isSuccessful){
                val data = response.body()
                if(data != null){
                    val cuaca = CuacaEntity(
                        data.weather.temp,
                        data.weather.cloud,
                        data.weather.windSpeed,
                        data.weather.humidity)
                    dataCuaca.postValue(cuaca)
                }else{
                    Log.e("Cuaca", "Error: ${response.message()}")
                }
            }
        }
        override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            Log.e("Cuaca", "Error: ${t.message}")
        }
    })
        return dataCuaca
    }

}