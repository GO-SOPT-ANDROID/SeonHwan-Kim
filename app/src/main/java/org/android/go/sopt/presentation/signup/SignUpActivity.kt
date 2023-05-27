package org.android.go.sopt.presentation.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.showShortToast

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel by viewModels<SIgnUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener {
            hideKeyboard(binding.root)
        }

        this.signUpBtnEnabled()
        this.onClickComplete()
    }

    private fun onClickComplete() {
        viewModel.signUpResult.observe(this) { data ->
            when (data) {
                200 -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    if (!isFinishing) finish()
                }

                else -> {
                    showShortToast("문제가 발생하였습니다!")
                }
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
