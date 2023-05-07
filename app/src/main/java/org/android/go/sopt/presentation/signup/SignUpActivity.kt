package org.android.go.sopt.presentation.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.android.go.sopt.R
import org.android.go.sopt.data.local.User
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.SoptApplication
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.model.RequestSignUpDto
import org.android.go.sopt.data.remote.model.ResponseSignUpDto
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.showShortToast
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val signUpService = ServicePool.signUpService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener {
            hideKeyboard(binding.root)
        }

        this.onClickComplete()
    }

    private fun onClickComplete() {
        with(binding) {
            btSignupComplete.setOnClickListener {
                val user = User(
                    etSignupId.text.toString(),
                    etSignupPassword.text.toString(),
                    etSignupName.text.toString(),
                    etSignupSpecialty.text.toString(),
                )

                if (user.id?.length !in 6..10) {
                    showShortToast(getString(R.string.sign_up_id_err_msg))
                } else if (user.password?.length !in 8..12) {
                    showShortToast(getString(R.string.sign_up_password_err_msg))
                } else if (user.name?.isEmpty() == true) {
                    showShortToast(getString(R.string.sign_up_name_err_msg))
                } else if (user.specialty?.isEmpty() == true) {
                    showShortToast(getString(R.string.sign_up_specialty_err_msg))
                } else {
                    signUpService.signUp(
                        RequestSignUpDto(
                            etSignupId.text.toString(),
                            etSignupPassword.text.toString(),
                            etSignupName.text.toString(),
                            etSignupSpecialty.text.toString()
                        )
                    ).enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
                        override fun onResponse(
                            call: Call<ResponseSignUpDto>,
                            response: Response<ResponseSignUpDto>
                        ) {
                            if (response.isSuccessful) {
                                response.body()?.message?.let { showShortToast(it) } ?: getString(R.string.login_success_sign_up_msg)
                                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                                startActivity(intent)
                                if (!isFinishing) finish()
                            } else {
                                response.body()?.message?.let { showShortToast(it) }
                            }
                        }

                        override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                            t.message?.let { showShortToast(it) }
                        }

                    })
                }
            }
        }
    }
}