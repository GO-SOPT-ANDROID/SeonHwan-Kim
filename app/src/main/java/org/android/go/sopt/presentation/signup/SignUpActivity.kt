package org.android.go.sopt.presentation.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.android.go.sopt.R
import org.android.go.sopt.data.User
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.util.IntentKey
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.showShortToast

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

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
                val id = etSignupId.text
                val password = etSignupPassword.text
                val name = etSignupName.text
                val specialty = etSignupSpecialty.text

                if (id.length !in 6..10) {
                    showShortToast(getString(R.string.sign_up_id_err_msg))
                } else if (password.length !in 8..12) {
                    showShortToast(getString(R.string.sign_up_password_err_msg))
                } else if (name.isEmpty()) {
                    showShortToast(getString(R.string.sign_up_name_err_msg))
                } else if (specialty.isEmpty()) {
                    showShortToast(getString(R.string.sign_up_specialty_err_msg))
                } else {
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    saveUserInformation(
                        id.toString(),
                        password.toString(),
                        name.toString(),
                        specialty.toString()
                    )
                    setResult(RESULT_OK, intent)
                    // 만약 종료가 되지 않았다면 종료시키기
                    if (!isFinishing) finish()
                }
            }
        }
    }

    private fun saveUserInformation(id: String, password: String, name: String, specialty: String) {
        val sharedPreferences = getSharedPreferences(KEY_PREFS, 0)

        sharedPreferences.edit().run {
            putBoolean(KEY_ISLOGIN, false)
            putString(KEY_ID, id)
            putString(KEY_PASSWORD, password)
            putString(KEY_NAME, name)
            putString(KEY_SPECIALTY, specialty)
        }.apply()
    }

    companion object {
        private const val KEY_PREFS = "userInfo"
        private const val KEY_ISLOGIN = "isLogin"
        private const val KEY_ID = "id"
        private const val KEY_PASSWORD = "password"
        private const val KEY_NAME = "name"
        private const val KEY_SPECIALTY = "specialty"
    }
}