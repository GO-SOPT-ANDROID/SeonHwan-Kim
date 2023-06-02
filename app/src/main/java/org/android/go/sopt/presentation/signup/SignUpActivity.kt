package org.android.go.sopt.presentation.signup

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.showShortToast
import org.android.go.sopt.util.state.UiState.Error
import org.android.go.sopt.util.state.UiState.Failure
import org.android.go.sopt.util.state.UiState.Success

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.vm = viewModel

        signUp()
        validInput()
    }

    private fun signUp() {
        viewModel.signUpState.observe(this) { state ->
            when (state) {
                is Success -> {
                    if (!isFinishing) finish()
                    showShortToast("회원가입을 완료하였습니다")
                }

                is Failure -> showShortToast("필수 정보를 입력해주세요")
                is Error -> showShortToast("문제가 발생하였습니다")
            }
        }
    }

    private fun validInput() {
        viewModel.isValidId.observe(this) {
            if (viewModel.isValidId.value == false && viewModel.id.value != "") {
                binding.etSignupId.error = "id는 6글자 이상 10글자 이하로 작성해주세요"
            } else {
                binding.etSignupId.error = null
                binding.etSignupId.isErrorEnabled = false
            }
        }
        viewModel.isValidPassword.observe(this) {
            if (viewModel.isValidPassword.value == false && viewModel.password.value != "") {
                binding.etSignupPassword.error = "영문, 숫자, 특수문자가 포함되어야 하며 6~12글자 이내"
            } else {
                binding.etSignupPassword.error = null
                binding.etSignupPassword.isErrorEnabled = false
            }
        }
    }
}
