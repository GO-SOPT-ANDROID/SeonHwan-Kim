package org.android.go.sopt.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.presentation.main.MainActivity
import org.android.go.sopt.presentation.signup.SignUpActivity
import org.android.go.sopt.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        setContentView(binding.root)

        binding.root.setOnClickListener {
            hideKeyboard(binding.root)
        }

        viewModel.autoLogin()

        this.onClickLogin()
        this.onClickSignUp()
    }

    private fun onClickLogin() {
        viewModel.signIn.observe(this) { data ->
            when (data) {
                200 -> navigateToMainActivity()
                else -> showShortToast("id 또는 password가 일치하지 않습니다.")
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        showShortToast("로그인 성공")
        if (!isFinishing) finish()
    }

    private fun onClickSignUp() {
        binding.btMainSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
