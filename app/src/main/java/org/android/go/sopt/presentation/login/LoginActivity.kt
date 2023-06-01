package org.android.go.sopt.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.presentation.main.MainActivity
import org.android.go.sopt.presentation.signup.SignUpActivity
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.hideKeyboard
import org.android.go.sopt.util.extension.showShortToast
import org.android.go.sopt.util.state.UiState.Error
import org.android.go.sopt.util.state.UiState.Failure
import org.android.go.sopt.util.state.UiState.Success

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        setContentView(binding.root)

        binding.root.setOnClickListener {
            hideKeyboard(binding.root)
        }

        viewModel.autoLogin()

        this.onClickLogin()
        this.onClickSignUp()
    }

    private fun onClickLogin() {
        viewModel.signInState.observe(this) { state ->
            when (state) {
                is Success -> {
                    navigateToMainActivity()
                }

                is Failure -> {
                    showShortToast("아이디 혹은 비밀번호가 잘못되었습니다")
                }

                is Error -> {
                    showShortToast("문제가 발생하였습니다")
                }
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
