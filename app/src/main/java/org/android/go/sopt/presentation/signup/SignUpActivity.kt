package org.android.go.sopt.presentation.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.android.go.sopt.R
import org.android.go.sopt.data.User
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.SoptApplication
import org.android.go.sopt.presentation.login.LoginActivity
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
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    SoptApplication.prefs.saveUserInformation(
                        false,
                        user
                    )
                    setResult(RESULT_OK, intent)
                    // 만약 종료가 되지 않았다면 종료시키기
                    if (!isFinishing) finish()
                }
            }
        }
    }
}