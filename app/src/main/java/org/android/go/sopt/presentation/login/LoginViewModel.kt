package org.android.go.sopt.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.go.sopt.SoptApplication
import org.android.go.sopt.data.model.request.RequestSignInDto
import org.android.go.sopt.domain.repository.AuthRepository
import org.android.go.sopt.util.state.UiState
import org.android.go.sopt.util.state.UiState.Error
import org.android.go.sopt.util.state.UiState.Failure
import org.android.go.sopt.util.state.UiState.Success
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    val id = MutableLiveData("")
    val password = MutableLiveData("")

    private val _signInState = MutableLiveData<UiState>()
    val signInState: LiveData<UiState>
        get() = _signInState

    fun onClickSignIn() {
        viewModelScope.launch {
            authRepository.signIn(
                RequestSignInDto(
                    id.value.toString(),
                    password.value.toString(),
                ),
            )
                .onSuccess {
                    SoptApplication.prefs.setBoolean(KEY_ISLOGIN, true)
                    _signInState.value = Success
                }
                .onFailure { throwable ->
                    if (throwable is HttpException) {
                        when (throwable.code()) {
                            INVALID_INPUT -> {
                                _signInState.value = Failure(INVALID_INPUT)
                            }

                            else -> {
                                _signInState.value = Error
                            }
                        }
                    }
                }
        }
    }

    fun autoLogin() {
        if (SoptApplication.prefs.getBoolean(KEY_ISLOGIN, false)) {
            _signInState.value = Success
        }
    }

    companion object {
        const val KEY_ISLOGIN = "isLogin"
        const val INVALID_INPUT = 400
    }
}
