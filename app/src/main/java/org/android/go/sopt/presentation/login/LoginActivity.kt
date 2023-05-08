package org.android.go.sopt.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.R
import org.android.go.sopt.presentation.main.MainActivity
import org.android.go.sopt.presentation.signup.SignUpActivity
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.SoptApplication
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.model.RequestSignInDto
import org.android.go.sopt.data.remote.model.ResponseSignInDto
import org.android.go.sopt.data.remote.service.SignInService
import org.android.go.sopt.util.*
import retrofit2.Call
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val signInService = ServicePool.signInService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener {
            hideKeyboard(binding.root)
        }

        this.autoLogin()
        this.onClickLogin()
        this.onClickSignUp()
    }

    private fun onClickLogin() {
        with(binding) {
            btMainLogin.setOnClickListener {
                signInService.signIn(
                    RequestSignInDto(
                        etMainId.text.toString(),
                        etMainPassword.text.toString(),
                    )
                ).enqueue(object : retrofit2.Callback<ResponseSignInDto> {
                    override fun onResponse(
                        call: Call<ResponseSignInDto>,
                        response: Response<ResponseSignInDto>
                    ) {
                        if (response.isSuccessful) {
                            SoptApplication.prefs.setBoolean(KEY_ISLOGIN, true)
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            showShortToast(getString(R.string.login_success_login_msg))
                            if (!isFinishing) finish()
                        } else {
                            response.body()?.message?.let { showShortToast(it) }
                        }
                    }

                    override fun onFailure(call: Call<ResponseSignInDto>, t: Throwable) {
                        t.message?.let { showShortToast(it) }
                    }
                })
            }
        }
    }


    private fun onClickSignUp() {
        binding.btMainSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun autoLogin() {
        if (SoptApplication.prefs.getBoolean(KEY_ISLOGIN, false)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            showShortToast(getString(R.string.login_success_login_msg))
            if (!isFinishing) finish()
        }
    }

    companion object {
        private const val KEY_ISLOGIN = "isLogin"
    }
}
