package org.android.go.sopt.presentation.signup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.showShortToast
import org.android.go.sopt.util.state.UiState.Failure
import org.android.go.sopt.util.state.UiState.Success
import org.android.go.sopt.util.state.UiState.Error

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.vm = viewModel

        signUp()
        this.signUpBtnEnabled()
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

    private fun signUpBtnEnabled() {
        with(binding) {
            listOf(etSignupId, etSignupPassword, etSignupName, etSignupSpecialty).forEach {
                it.doAfterTextChanged {
                    btSignupComplete.isEnabled = isAllConditionSatisfy()
                }
            }
        }
    }

    private fun isAllConditionSatisfy(): Boolean {
        with(binding) {
            return etSignupId.text.length in 6..10 &&
                etSignupPassword.text.length in 8..12 &&
                etSignupName.text.isNotBlank() &&
                etSignupSpecialty.text.isNotBlank()
        }
    }
}
