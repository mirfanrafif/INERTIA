package com.inertia.ui.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inertia.BuildConfig
import com.inertia.MyApplication
import com.inertia.R
import com.inertia.databinding.ActivityMainBinding
import com.inertia.ui.form.FormActivity
import com.inertia.ui.home.HomeFragment
import com.inertia.ui.login.LoginActivity
import com.inertia.ui.profile.ProfileFragment
import com.inertia.utils.ViewModelFactory
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAKE_PICTURE = 100
    }
    private val AUTHORITY = BuildConfig.APPLICATION_ID + ".provider"
    private lateinit var imageUri : Uri //uri lokasi dari foto
    private lateinit var output : File


    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels {
        factory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val homeFragment = HomeFragment.getInstance()
        val profileFragment = ProfileFragment.getInstance()
        moveFragment(homeFragment)

        binding.btnReport.setOnClickListener {
            report()
        }
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> moveFragment(homeFragment)
                R.id.nav_profile -> moveFragment(profileFragment)
            }
            true
        }
    }

    private fun report() {

        if (viewModel.getUser().nomorWa != null) {
            val randNumber = Random(30).nextInt(100)
            val date = SimpleDateFormat("yyyymmdhhmmss", Locale.getDefault()).format(Date())
            val fileName = "$date-$randNumber.jpg"

            output = File(File(filesDir, "photos"), fileName)
            if (output.exists()) output.delete() else output.parentFile.mkdirs()

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            imageUri = FileProvider.getUriForFile(this, AUTHORITY, output)
            imageUri.path?.let { Log.d("Photos", it) }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, TAKE_PICTURE)
        }else{
            Toast.makeText(this, "Silakan login terlebih dahulu", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun moveFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.wrapper, fragment)
            commit()
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TAKE_PICTURE) {
            if(resultCode == Activity.RESULT_OK) {
                contentResolver.notifyChange(imageUri, null)
                val intent = Intent(this, FormActivity::class.java)
                intent.putExtra(FormActivity.EXTRA_IMG_URI, imageUri)
                intent.putExtra(FormActivity.EXTRA_FILE, output)
                startActivity(intent)
            }
        }
    }
}