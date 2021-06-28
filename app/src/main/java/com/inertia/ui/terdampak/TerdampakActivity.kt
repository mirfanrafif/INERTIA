package com.inertia.ui.terdampak

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.inertia.MyApplication
import com.inertia.core.datasource.local.entity.TerdampakEntity
import com.inertia.core.datasource.local.entity.UserEntity
import com.inertia.core.datasource.remote.api.InertiaService
import com.inertia.core.datasource.remote.response.TerdampakResponse
import com.inertia.databinding.ActivityTerdampakBinding
import com.inertia.ui.main.MainViewModel
import com.inertia.utils.ViewModelFactory
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class TerdampakActivity : AppCompatActivity() {
    companion object {
        const val USER = "user"
    }

    private lateinit var binding: ActivityTerdampakBinding
    private var terdampakResponseItem: ArrayList<TerdampakResponse> = ArrayList()
    private lateinit var nomorWA: String

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels { factory }
    private var user: UserEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).appComponent.inject(this)
        binding = ActivityTerdampakBinding.inflate(layoutInflater)
        user = viewModel.getUser()
        setContentView(binding.root)
        getData()
    }

    private fun getData() {
        nomorWA = user?.nomorWa.toString()

        val terdampakAdapter = TerdampakAdapter()
        InertiaService().getTerdampak().getAllTerdampak(nomorWA).enqueue(object :
            retrofit2.Callback<List<TerdampakResponse>> {
            override fun onResponse(
                call: Call<List<TerdampakResponse>>,
                response: Response<List<TerdampakResponse>>,
            ) {

                val data = response.body()
                when {
                    data?.isNotEmpty()  == true -> {
                        terdampakResponseItem = response.body() as ArrayList<TerdampakResponse>
                        val dataArray: ArrayList<TerdampakEntity> = ArrayList()
                        terdampakResponseItem.forEach {
                            val s = TerdampakEntity(
                                it.idKasus,
                                it.nomorWa,
                                it.idBencana,
                                it.idSub,
                                it.nama,
                                it.alamat,
                                it.provinsi,
                                it.kota,
                                it.tanggal,
                                it.namaBencana
                            )
                            dataArray.add(s)
                        }
                        terdampakAdapter.setData(dataArray)
                        binding.progressBar.visibility = View.GONE
                    }
                    data?.isEmpty() == true -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvEmpty.visibility = View.VISIBLE
                    }
                }
            }

            override fun onFailure(call: Call<List<TerdampakResponse>>, t: Throwable) {
                Log.e("Gagal", "Error: ${t.message}")
            }
        })

        with(binding.rvTerdampak) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = terdampakAdapter
        }
    }
}