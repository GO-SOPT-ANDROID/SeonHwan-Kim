package org.android.go.sopt.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.R
import org.android.go.sopt.presentation.main.MainActivity
import org.android.go.sopt.presentation.signup.SignUpActivity
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.SoptApplication
import org.android.go.sopt.util.*


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

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
                if (SoptApplication.prefs.getString(
                        KEY_ID,
                        null
                    ) == etMainId.text.toString() && SoptApplication.prefs.getString(
                        KEY_PASSWORD, null
                    ) == etMainPassword.text.toString()
                ) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    SoptApplication.prefs.setBoolean(KEY_ISLOGIN, true)
                    startActivity(intent)
                    showShortToast(getString(R.string.login_success_login_msg))
                    if (!isFinishing) finish()
                } else {
                    showShortToast(getString(R.string.login_fail_login_msg))
                }
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
        private const val KEY_ID = "id"
        private const val KEY_PASSWORD = "password"
    }
}
