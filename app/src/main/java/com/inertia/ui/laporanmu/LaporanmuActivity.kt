package com.inertia.ui.laporanmu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.inertia.MyApplication
import com.inertia.databinding.ActivityLaporanmuBinding
import com.inertia.ui.home.BencanaAdapter
import com.inertia.utils.ViewModelFactory
import com.mirfanrafif.kicksfilm.vo.Status
import javax.inject.Inject

class LaporanmuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaporanmuBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: LaporanmuViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).coreComponent.inject(this)
        binding = ActivityLaporanmuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val adapter = BencanaAdapter()
        val layoutmanager = GridLayoutManager(this, 2)
        binding.rvLaporanmu.adapter = adapter
        binding.rvLaporanmu.layoutManager = layoutmanager
        val nomorWa = viewModel.getUser().nomorWa
        if (nomorWa != null) {
            viewModel.getBencanaByNomorWa(nomorWa).observe(this, {
                when(it.status) {
                    Status.SUCCESS -> {
                        if (it.data != null) {
                            adapter.setData(it.data)
                        }
                        binding.laporanmuProgressbar.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                        if (it.data != null) {
                            adapter.setData(it.data)
                        }
                        binding.laporanmuProgressbar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.laporanmuProgressbar.visibility = View.VISIBLE
                    }
                }
            })
        }

    }
}