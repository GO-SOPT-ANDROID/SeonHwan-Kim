package org.android.go.sopt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivityLoginBinding

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

        this.onClickLogin()
        this.onCLickSignUp()
    }

    private fun onClickLogin() {
        binding.btMainLogin.setOnClickListener {
            if(id == binding.etMainId.text.toString() && password == binding.etMainPassword.text.toString()){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("specialty", specialty)
                startActivity(intent)
                Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
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
    ){ result: ActivityResult ->
        if(result.resultCode == RESULT_OK){
            id = result.data?.getStringExtra("id").toString()
            password = result.data?.getStringExtra("password").toString()
            name = result.data?.getStringExtra("name").toString()
            specialty = result.data?.getStringExtra("specialty").toString()
            Snackbar.make(binding.root, "회원 가입이 완료되었습니다!", Snackbar.LENGTH_SHORT).show()
        }
    }
}