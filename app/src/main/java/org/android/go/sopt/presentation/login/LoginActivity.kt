package org.android.go.sopt.presentation.login

import android.content.Intent
import android.os.Build
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
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.showShortSnackbar
import org.android.go.sopt.util.showShortToast


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener {
            hideKeyboard(binding.root)
        }

        this.onClickLogin()
        this.onCLickSignUp()
    }

    private fun onClickLogin() {
        with(binding){
        btMainLogin.setOnClickListener {
                if (user?.id == etMainId.text.toString() && user?.password == etMainPassword.text.toString()) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                    showShortToast(getString(R.string.login_success_login_msg))
                    if(!isFinishing) finish()
                } else {
                    showShortToast(getString(R.string.login_fail_login_msg))
                }
            }
        }
    }


    private val getResultSignUp = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            user = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                result.data?.getParcelableExtra("user", User::class.java)
            } else{
                result.data?.getParcelableExtra("user")
            }
            Log.d("user", "---------\n$user")
            showShortSnackbar(binding.root, getString(R.string.login_success_sign_up_msg))
        }
    }

    private fun onCLickSignUp() {
        binding.btMainSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getResultSignUp.launch(intent)
        }
    }
}
