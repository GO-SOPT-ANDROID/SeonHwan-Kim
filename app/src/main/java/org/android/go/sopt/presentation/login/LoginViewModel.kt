package org.android.go.sopt.presentation.login

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
import retrofit2.Call
import retrofit2.Response

class LoginViewModel: ViewModel() {
    val id = MutableLiveData("")
    val password = MutableLiveData("")

    private val _signIn = MutableLiveData<Int>()
    val signIn: LiveData<Int> get() = _signIn

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
                    _signIn.value = response.body()?.status
                } else {
                    Log.d("response err", response.body().toString())
                }
            }

            override fun onFailure(call: Call<BaseResponseDto<ResponseSignInDto>>, t: Throwable) {
                Log.d("errrr", t.message.toString())
            }
        })
    }

    fun autoLogin() {
        if (SoptApplication.prefs.getBoolean(KEY_ISLOGIN, false)) {
            _signIn.value = 200
        }
    }

    companion object{
        const val KEY_ISLOGIN = "isLogin"
    }
}