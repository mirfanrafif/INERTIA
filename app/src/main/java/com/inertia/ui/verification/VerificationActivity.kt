package com.inertia.ui.verification

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.inertia.MyApplication
import com.inertia.core.datasource.local.entity.UserEntity
import com.inertia.databinding.ActivityVerificationBinding
import com.inertia.ui.main.MainActivity
import com.inertia.utils.ViewModelFactory
import javax.inject.Inject

class VerificationActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_CODE = "extra_code"
    }

    private var user: UserEntity? = null
    private var code: String? = null

    private lateinit var binding: ActivityVerificationBinding

    @Inject lateinit var factory: ViewModelFactory

    private val viewModel: VerificationViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).appComponent.inject(this)

        user = intent.getParcelableExtra(EXTRA_USER)
        code = intent.getStringExtra(EXTRA_CODE)

        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnVerify.setOnClickListener {
                verify(otpView.text.toString())
            }
            otpView.setOtpCompletionListener {
                verify(it)
            }
        }
    }

    private fun verify(otp: String) {
        if (otp == code) {
            viewModel.saveUser(user)
            startActivity(Intent(this, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            finish()
        }else{
            binding.otpView.setLineColor(Color.RED)
            binding.otpView.setText("")
        }
    }


}