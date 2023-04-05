package org.android.go.sopt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.onClickComplete()
    }

    private fun onClickComplete(){
        binding.btSignupComplete.setOnClickListener{
            if(binding.etSignupId.length() !in 6..10){
                Toast.makeText(applicationContext, "id는 6글자 이상 10글자 이하으로 작성해주세요", Toast.LENGTH_SHORT).show()
            } else if(binding.etSignupPassword.length() !in 8..12){
                Toast.makeText(applicationContext, "password는 8글자 이상 12글자 이하로 작성해주세요", Toast.LENGTH_SHORT).show()
            } else if(binding.etSignupName.length() == 0){
                Toast.makeText(applicationContext, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if(binding.etSignupSpecialty.length() == 0){
                Toast.makeText(applicationContext, "특기를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else{
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("id", binding.etSignupId.text.toString())
                intent.putExtra("password", binding.etSignupPassword.text.toString())
                intent.putExtra("name", binding.etSignupName.text.toString())
                intent.putExtra("specialty", binding.etSignupSpecialty.text.toString())
                Snackbar.make(binding.root, "회원 가입이 완료되었습니다!", Snackbar.LENGTH_SHORT).show()
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}