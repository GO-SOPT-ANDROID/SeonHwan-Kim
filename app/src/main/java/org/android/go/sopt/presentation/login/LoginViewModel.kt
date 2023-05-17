package org.android.go.sopt.presentation.login

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.R
import org.android.go.sopt.SoptApplication
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.model.BaseResponseDto
import org.android.go.sopt.data.remote.model.RequestSignInDto
import org.android.go.sopt.data.remote.model.ResponseSignInDto
import org.android.go.sopt.presentation.main.MainActivity
import org.android.go.sopt.util.showShortToast
import retrofit2.Call
import retrofit2.Response

class LoginViewModel: ViewModel() {
    val id = MutableLiveData("")
    val password = MutableLiveData("")

    private val _signIn = MutableLiveData<BaseResponseDto<ResponseSignInDto>>()
    val signIn: LiveData<BaseResponseDto<ResponseSignInDto>> get() = _signIn

    fun onClickLogin(){
        signIn()
    }

    private fun signIn(){
        ServicePool.signInService.signIn(
            RequestSignInDto(
                id.value.toString(), password.value.toString()
            )
        ).enqueue(object : retrofit2.Callback<BaseResponseDto<ResponseSignInDto>> {
            override fun onResponse(
                call: Call<BaseResponseDto<ResponseSignInDto>>,
                response: Response<BaseResponseDto<ResponseSignInDto>>
            ) {
                if (response.isSuccessful) {
                    SoptApplication.prefs.setBoolean(KEY_ISLOGIN, true)
                    _signIn.value = response.body()
                } else {
                    Log.d("response err", response.body().toString())
                }
            }

            override fun onFailure(call: Call<BaseResponseDto<ResponseSignInDto>>, t: Throwable) {
                Log.d("errrr", t.message.toString())
            }
        })
    }

    companion object{
        const val KEY_ISLOGIN = "isLogin"
    }
}