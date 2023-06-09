package org.android.go.sopt.presentation.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.doAfterTextChanged
import org.android.go.sopt.R
import org.android.go.sopt.data.local.User
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.SoptApplication
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.model.BaseResponseDto
import org.android.go.sopt.data.remote.model.RequestSignUpDto
import org.android.go.sopt.data.remote.model.ResponseSignUpDto
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.showShortToast
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

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
        with(binding) {
            btSignupComplete.setOnClickListener {
                ServicePool.signUpService.signUp(
                    RequestSignUpDto(
                        etSignupId.text.toString(),
                        etSignupPassword.text.toString(),
                        etSignupName.text.toString(),
                        etSignupSpecialty.text.toString()
                    )
                ).enqueue(object : retrofit2.Callback<BaseResponseDto<ResponseSignUpDto>> {
                    override fun onResponse(
                        call: Call<BaseResponseDto<ResponseSignUpDto>>, response: Response<BaseResponseDto<ResponseSignUpDto>>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.message?.let { showShortToast(it) }
                                ?: getString(R.string.login_success_sign_up_msg)
                            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                            startActivity(intent)
                            if (!isFinishing) finish()
                        } else {
                            response.body()?.message?.let { showShortToast(it) }
                        }
                    }

                    override fun onFailure(call: Call<BaseResponseDto<ResponseSignUpDto>>, t: Throwable) {
                        t.message?.let { showShortToast(it) }
                    }

                })
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