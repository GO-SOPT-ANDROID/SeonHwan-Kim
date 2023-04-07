package org.android.go.sopt.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.android.go.sopt.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var name: String
    private lateinit var specialty: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.getMyData()
    }

    private fun getMyData() {
        name = intent.getStringExtra("name").toString()
        specialty = intent.getStringExtra("specialty").toString()
        binding.tvMainName.text = "이름 : $name"
        binding.tvMainSpecialty.text = "특기 : $specialty"
    }
}