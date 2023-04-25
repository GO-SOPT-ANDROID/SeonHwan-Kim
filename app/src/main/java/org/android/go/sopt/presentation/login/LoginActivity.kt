package org.android.go.sopt.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.R
import org.android.go.sopt.data.User
import org.android.go.sopt.presentation.main.MainActivity
import org.android.go.sopt.presentation.signup.SignUpActivity
import org.android.go.sopt.databinding.ActivityLoginBinding
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
        this.onCLickSignUp()
    }

    private fun onClickLogin() {
        with(binding) {
            btMainLogin.setOnClickListener {
                val sharedPreferences = getSharedPreferences(KEY_PREFS, 0)

                if (sharedPreferences.getString(
                        KEY_ID,
                        null
                    ) == etMainId.text.toString() && sharedPreferences.getString(
                        KEY_PASSWORD, null
                    ) == etMainPassword.text.toString()
                ){
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    sharedPreferences.edit().putBoolean(KEY_ISLOGIN, true).apply()
                    startActivity(intent)
                    showShortToast(getString(R.string.login_success_login_msg))
                    if(!isFinishing) finish()
                } else{
                    showShortToast(getString(R.string.login_fail_login_msg))
                }
            }
        }
    }

    private val getResultSignUp = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            showShortSnackbar(binding.root, getString(R.string.login_success_sign_up_msg))
        }
    }

    private fun onCLickSignUp() {
        binding.btMainSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getResultSignUp.launch(intent)
        }
    }

    private fun autoLogin() {
        val sharedPreferences = getSharedPreferences(KEY_PREFS, 0)

        if (sharedPreferences.getBoolean(KEY_ISLOGIN, false)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            showShortToast(getString(R.string.login_success_login_msg))
            if (!isFinishing) finish()
        }
    }

    companion object {
        private const val KEY_PREFS = "userInfo"
        private const val KEY_ISLOGIN = "isLogin"
        private const val KEY_ID = "id"
        private const val KEY_PASSWORD = "password"
    }
}
