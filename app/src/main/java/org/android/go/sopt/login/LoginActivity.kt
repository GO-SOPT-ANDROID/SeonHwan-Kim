package org.android.go.sopt.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.R
import org.android.go.sopt.main.MainActivity
import org.android.go.sopt.signup.SignUpActivity
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.showShortSnackbar
import org.android.go.sopt.util.showShortToast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var id: String
    private lateinit var password: String
    private lateinit var name: String
    private lateinit var specialty: String

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
        binding.btMainLogin.setOnClickListener {
            if (id == binding.etMainId.text.toString() && password == binding.etMainPassword.text.toString()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("specialty", specialty)
                startActivity(intent)
                showShortToast(getString(R.string.login_success_login_msg))
            } else {
                showShortToast(getString(R.string.login_fail_login_msg))
            }
        }
    }

    private fun onCLickSignUp() {
        binding.btMainSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getResultSignUp.launch(intent)
        }
    }

    private val getResultSignUp = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            id = result.data?.getStringExtra("id") ?: ""
            password = result.data?.getStringExtra("password") ?: ""
            name = result.data?.getStringExtra("name") ?: ""
            specialty = result.data?.getStringExtra("specialty") ?: ""
            showShortSnackbar(binding.root, getString(R.string.login_success_sign_up_msg))
        }
    }
}
