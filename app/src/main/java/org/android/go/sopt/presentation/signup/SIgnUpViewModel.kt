package org.android.go.sopt.presentation.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.model.BaseResponseDto
import org.android.go.sopt.data.remote.model.RequestSignUpDto
import org.android.go.sopt.data.remote.model.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Response

class SIgnUpViewModel : ViewModel() {
    val id = MutableLiveData("")
    val password = MutableLiveData("")
    val name = MutableLiveData("")
    val skill = MutableLiveData("")

    val isSignUpBtnEnabled = MutableLiveData(false)

    private val _signUpResult = MutableLiveData<Int>()
    val signUpResult: LiveData<Int> get() = _signUpResult

    fun signUp() {
        ServicePool.signUpService.signUp(
            RequestSignUpDto(
                id.value.toString(),
                password.value.toString(),
                name.value.toString(),
                skill.value.toString(),
            ),
        ).enqueue(object : retrofit2.Callback<BaseResponseDto<ResponseSignUpDto>> {
            override fun onResponse(
                call: Call<BaseResponseDto<ResponseSignUpDto>>,
                response: Response<BaseResponseDto<ResponseSignUpDto>>,
            ) {
                if (response.isSuccessful) {
                    _signUpResult.value = response.body()?.status
                } else {
                    _signUpResult.value = response.body()?.status
                    Log.d("response err", response.message())
                }
            }

            override fun onFailure(call: Call<BaseResponseDto<ResponseSignUpDto>>, t: Throwable) {
                Log.d("request err", t.message.toString())
            }
        })
    }
}
