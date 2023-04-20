package org.android.go.sopt.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.initLayout()
        this.goToTop()
    }



    private fun initLayout() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.fcv_main, HomeFragment()).commit()
        }
        binding.bnvMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    this.changeFragment(HomeFragment())
                }
                R.id.menu_search -> {
                    this.changeFragment(SearchFragment())
                }
                else -> {
                    this.changeFragment(GalleryFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
    }


    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }

    private fun goToTop(){
        binding.bnvMain.setOnItemReselectedListener {
            when(it.itemId){
                R.id.menu_home -> {
                    val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
                    if(currentFragment is HomeFragment){
                        currentFragment.scrollToTop()
                    }
                }
            }
        }
    }
}