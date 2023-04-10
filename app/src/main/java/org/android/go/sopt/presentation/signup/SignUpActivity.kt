package org.android.go.sopt.presentation.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignUpBinding
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

    private fun onClickComplete(){
        binding.btSignupComplete.setOnClickListener{
            val id = binding.etSignupId.text
            val password = binding.etSignupPassword.text
            val name = binding.etSignupName.text
            val specialty = binding.etSignupSpecialty.text

            if(id.length !in 6..10){
                showShortToast(getString(R.string.sign_up_id_err_msg))
            } else if(password.length !in 8..12){
                showShortToast(getString(R.string.sign_up_password_err_msg))
            } else if(name.isEmpty()){
                showShortToast(getString(R.string.sign_up_name_err_msg))
            } else if(specialty.isEmpty()){
                showShortToast(getString(R.string.sign_up_specialty_err_msg))
            } else{
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("id", id.toString())
                intent.putExtra("password", password.toString())
                intent.putExtra("name", name.toString())
                intent.putExtra("specialty", specialty.toString())
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}