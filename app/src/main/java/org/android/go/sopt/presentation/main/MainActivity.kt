package org.android.go.sopt.presentation.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.android.go.sopt.GalleryFragment
import org.android.go.sopt.HomeFragment
import org.android.go.sopt.R
import org.android.go.sopt.SearchFragment
import org.android.go.sopt.data.User
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.util.IntentKey
import org.android.go.sopt.util.getParcelable


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
//        if (currentFragment == null) {
//            supportFragmentManager.beginTransaction().add(R.id.fcv_main, HomeFragment()).commit()
//        }
//        binding.bnvMain.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.menu_home -> {
//                    HomeFragment()
//                    return@setOnItemSelectedListener true
//                }
//                R.id.menu_search -> {
//                    SearchFragment()
//                    return@setOnItemSelectedListener true
//                }
//                else -> {
//                    GalleryFragment()
//                    return@setOnItemSelectedListener true
//
//                }
//            }
//        }

        this.getUserData()
    }

    private fun getUserData() {
        val user: User? = intent.getParcelable(IntentKey.USER_DATA, User::class.java)
        binding.tvMainName.text = "이름 : ${user?.name}"
        binding.tvMainSpecialty.text = "특기 : ${user?.specialty}"
    }

//    private fun changeFragment(fragment: Fragment) {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fcv_main, fragment)
//            .commit()
//    }
}