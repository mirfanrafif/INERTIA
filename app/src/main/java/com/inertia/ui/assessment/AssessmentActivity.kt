package com.inertia.ui.assessment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.inertia.R
import com.inertia.core.datasource.local.entity.BencanaEntity
import com.inertia.core.datasource.local.entity.PenilaianEntity
import com.inertia.core.datasource.local.entity.UserEntity
import com.inertia.core.datasource.remote.api.InertiaService
import com.inertia.core.datasource.remote.response.StoreFormPenilaianResponse
import com.inertia.core.entity.SpinnerKeyValue
import com.inertia.core.response.SkalaResponse
import com.inertia.core.response.SubSektorResponse
import com.inertia.databinding.ActivityAssessmentBinding
import com.inertia.ui.terdampak.TerdampakActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AssessmentActivity : AppCompatActivity() {
    companion object {
        const val USER = "user"
        const val BENCANA = "bencana"
    }

    private lateinit var binding: ActivityAssessmentBinding
    private var alternatifs: ArrayList<SkalaResponse> = ArrayList()
    private var subSektors: ArrayList<SubSektorResponse> = ArrayList()

    private var jenisBencana: String? = null
    private var nomorWa: String? = null
    private var date: String? =
        SimpleDateFormat("yyyy-m-d", Locale.getDefault()).format(Date()).toString()
    private var city: String? = null
    private var provinsi: String? = null
    private var subSektorItem: String? = null
    private var alternatifValue1: ArrayList<String> = ArrayList()
    private var alternatifValue2: ArrayList<String> = ArrayList()
    private var alternatifValue3: ArrayList<String> = ArrayList()
    private var alternatifValue4: ArrayList<String> = ArrayList()
    private var alternatifValue5: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssessmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailBencana = intent.getParcelableExtra<BencanaEntity>(BENCANA)
        val detailUser = intent.getParcelableExtra<UserEntity>(USER)

        jenisBencana = detailBencana?.jenisBencana
        city = detailBencana?.kota
        provinsi = detailBencana?.provinsi
        nomorWa = detailUser?.nomorWa

        binding.edtJenisBencana.setText(jenisBencana)
        binding.edtKota.setText(city)
        binding.edtProvinsi.setText(provinsi)
        initSpinner()

        binding.btnKirim.setOnClickListener {
            kirimAssessment()
        }
    }

    private fun kirimAssessment() {
        binding.apply {
            when {
                edtName.text?.isEmpty() == true -> {
                    edtName.error = "Kolom harus diisi"
                    edtName.requestFocus()
                    return
                }
                edtAlamat.text?.isEmpty() == true -> {
                    edtAlamat.error = "Kolom harus diisi"
                    edtAlamat.requestFocus()
                    return
                }
                edtJenisBencana.text?.isEmpty() == true -> {
                    edtJenisBencana.error = "Kolom harus diisi"
                    edtJenisBencana.requestFocus()
                }
                edtKota.text?.isEmpty() == true -> {
                    edtKota.error = "Kolom harus diisi"
                    edtKota.requestFocus()
                }
                edtProvinsi.text?.isEmpty() == true -> {
                    edtProvinsi.error = "Kolom harus diisi"
                    edtProvinsi.requestFocus()
                }
            }

            val valAlamat = binding.edtAlamat.text.toString()
            val valNama = binding.edtName.text.toString()
            val valKota = binding.edtKota.text.toString()
            val valProvinsi = binding.edtProvinsi.text.toString()
            val valPenilaian: ArrayList<PenilaianEntity> = ArrayList()
            val valJBencana = binding.edtJenisBencana.text.toString()

            val penilaianEntity1 = PenilaianEntity()
            val penilaianEntity2 = PenilaianEntity()
            val penilaianEntity3 = PenilaianEntity()
            val penilaianEntity4 = PenilaianEntity()
            val penilaianEntity5 = PenilaianEntity()

            penilaianEntity1.idKriteria = alternatifValue1[0]
            penilaianEntity1.idSkala = alternatifValue1[1]
            penilaianEntity1.namaSkala = alternatifValue1[2]

            valPenilaian.add(0, penilaianEntity1)

            penilaianEntity2.idKriteria = alternatifValue2[0]
            penilaianEntity2.idSkala = alternatifValue2[1]
            penilaianEntity2.namaSkala = alternatifValue2[2]

            valPenilaian.add(1, penilaianEntity2)

            penilaianEntity3.idKriteria = alternatifValue3[0]
            penilaianEntity3.idSkala = alternatifValue3[1]
            penilaianEntity3.namaSkala = alternatifValue3[2]

            valPenilaian.add(2, penilaianEntity3)

            penilaianEntity4.idKriteria = alternatifValue4[0]
            penilaianEntity4.idSkala = alternatifValue4[1]
            penilaianEntity4.namaSkala = alternatifValue4[2]

            valPenilaian.add(3, penilaianEntity4)

            penilaianEntity5.idKriteria = alternatifValue5[0]
            penilaianEntity5.idSkala = alternatifValue5[1]
            penilaianEntity5.namaSkala = alternatifValue5[2]

            valPenilaian.add(4, penilaianEntity5)
            val jsonPenilaian = Gson().toJson(valPenilaian)

            InertiaService().getPenilaian().storeFormPenilaian(
                nomorWa,
                valJBencana,
                subSektorItem?.toInt(),
                valNama,
                valAlamat,
                valProvinsi,
                valKota,
                date,
                jsonPenilaian,
            ).enqueue(object : Callback<StoreFormPenilaianResponse> {
                override fun onResponse(
                    call: Call<StoreFormPenilaianResponse>,
                    response: Response<StoreFormPenilaianResponse>,
                ) {
                    val data = response.body()
                    if (data != null) {
                        Toast.makeText(this@AssessmentActivity,
                            "Berhasil Disimpan",
                            Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@AssessmentActivity, TerdampakActivity::class.java)
                        startActivity(intent)
                    }
                    finish()
                }

                override fun onFailure(call: Call<StoreFormPenilaianResponse>, t: Throwable) {
                    println("Gagal dikirim" + t.message)
                }
            })
        }
    }

    private fun initSpinner() {
        val spSubSektor = binding.spinnerSubSektor
        InertiaService().getPenilaian().getSubSektors().enqueue(object :
            Callback<List<SubSektorResponse>> {
            override fun onResponse(
                call: Call<List<SubSektorResponse>>,
                response: Response<List<SubSektorResponse>>,
            ) {
                val data = response.body()
                if (data != null) {
                    subSektors = response.body() as ArrayList<SubSektorResponse>
                    val dataArray: ArrayList<SpinnerKeyValue> = ArrayList()
                    subSektors.forEach {
                        val s = SpinnerKeyValue()
                        s.key = it.id
                        s.value = it.nmSubSektor
                        dataArray.add(s)
                    }
                    val adapter: ArrayAdapter<SpinnerKeyValue> =
                        ArrayAdapter<SpinnerKeyValue>(this@AssessmentActivity,
                            R.layout.spinner_item,
                            dataArray)
                    spSubSektor.adapter = adapter
                    spSubSektor.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {

                            }

                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long,
                            ) {
                                subSektorItem = adapter.getItem(position)?.key.toString()
                            }

                        }
                }
            }

            override fun onFailure(call: Call<List<SubSektorResponse>>, t: Throwable) {
                Log.e("Alternatif", "Error: ${t.message}")
            }
        })

        val spKeadaanBangunan = binding.spinnerKriteria1
        val spKeadaanStrukturBangunan = binding.spinnerKriteria2
        val spKeadaanFisikBangunan = binding.spinnerKriteria3
        val spFungsiBangunan = binding.spinnerKriteria4
        val spKeadaanLainnya = binding.spinnerKriteria5

        InertiaService().getPenilaian().getSkalas().enqueue(object :
            Callback<List<SkalaResponse>> {
            override fun onResponse(
                call: Call<List<SkalaResponse>>,
                response: Response<List<SkalaResponse>>,
            ) {
                val data = response.body()
                if (data != null) {
                    alternatifs = response.body() as ArrayList<SkalaResponse>
                    val dataArray: ArrayList<SpinnerKeyValue> = ArrayList()
                    alternatifs.forEach {
                        val s = SpinnerKeyValue()
                        s.key = it.id
                        s.value = it.nmSkala
                        dataArray.add(s)
                    }

                    val adapter: ArrayAdapter<SpinnerKeyValue> =
                        ArrayAdapter<SpinnerKeyValue>(this@AssessmentActivity,
                            R.layout.spinner_item,
                            dataArray)
                    spKeadaanBangunan.adapter = adapter
                    spKeadaanStrukturBangunan.adapter = adapter
                    spKeadaanFisikBangunan.adapter = adapter
                    spFungsiBangunan.adapter = adapter
                    spKeadaanLainnya.adapter = adapter

                    spKeadaanBangunan.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {

                            }

                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long,
                            ) {
                                alternatifValue1.add(0, "1") // idKriteria
                                alternatifValue1.add(1,
                                    adapter.getItem(position)?.key.toString()) // idSkala
                                alternatifValue1.add(2,
                                    adapter.getItem(position)?.value.toString()) // namaSkala
                            }

                        }

                    spKeadaanStrukturBangunan.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {

                            }

                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long,
                            ) {
                                alternatifValue2.add(0, "2") // idKriteria
                                alternatifValue2.add(1,
                                    adapter.getItem(position)?.key.toString()) // idSkala
                                alternatifValue2.add(2,
                                    adapter.getItem(position)?.value.toString()) // namaSkala
                            }

                        }

                    spKeadaanFisikBangunan.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {

                            }

                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long,
                            ) {
                                alternatifValue3.add(0, "3") // idKriteria
                                alternatifValue3.add(1,
                                    adapter.getItem(position)?.key.toString()) // idSkala
                                alternatifValue3.add(2,
                                    adapter.getItem(position)?.value.toString()) // namaSkala
                            }

                        }

                    spFungsiBangunan.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {

                            }

                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long,
                            ) {
                                alternatifValue4.add(0, "3") // idKriteria
                                alternatifValue4.add(1,
                                    adapter.getItem(position)?.key.toString()) // idSkala
                                alternatifValue4.add(2,
                                    adapter.getItem(position)?.value.toString()) // namaSkala
                            }

                        }

                    spKeadaanLainnya.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {

                            }

                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long,
                            ) {
                                alternatifValue5.add(0, "4") // idKriteria
                                alternatifValue5.add(1,
                                    adapter.getItem(position)?.key.toString()) // idSkala
                                alternatifValue5.add(2,
                                    adapter.getItem(position)?.value.toString()) // namaSkala
                            }
                        }
                }
            }

            override fun onFailure(call: Call<List<SkalaResponse>>, t: Throwable) {
                Log.e("Alternatif", "Error: ${t.message}")
            }
        })
    }
}