package org.android.go.sopt.presentation.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
//    private lateinit var id: String
//    private lateinit var password: String
//    private lateinit var name: String
//    private lateinit var specialty: String
    private var user: User? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener {
            hideKeyboard(binding.root)
        }

//        this.onClickLogin()
        this.onCLickSignUp()
    }

//    private fun onClickLogin() {
//        with(binding){
//        btMainLogin.setOnClickListener {
//                if (id == etMainId.text.toString() && password == etMainPassword.text.toString()) {
//                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                    intent.putExtra("name", name)
//                    intent.putExtra("specialty", specialty)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent)
//                    showShortToast(getString(R.string.login_success_login_msg))
//                    if(!isFinishing) finish()
//                } else {
//                    showShortToast(getString(R.string.login_fail_login_msg))
//                }
//            }
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private val getResultSignUp = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            user = result.data?.getParcelableExtra("user", User::class.java)
            Log.d("user", "---------\n$user")
//            id = result.data?.getStringExtra("id") ?: ""
//            password = result.data?.getStringExtra("password") ?: ""
//            name = result.data?.getStringExtra("name") ?: ""
//            specialty = result.data?.getStringExtra("specialty") ?: ""
            showShortSnackbar(binding.root, getString(R.string.login_success_sign_up_msg))
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun onCLickSignUp() {
        binding.btMainSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getResultSignUp.launch(intent)
        }
    }
}
