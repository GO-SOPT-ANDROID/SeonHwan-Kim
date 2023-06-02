package org.android.go.sopt.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.domain.repository.AuthRepository
import org.android.go.sopt.presentation.login.LoginViewModel.Companion.INVALID_INPUT
import org.android.go.sopt.util.state.UiState
import org.android.go.sopt.util.state.UiState.Error
import org.android.go.sopt.util.state.UiState.Failure
import org.android.go.sopt.util.state.UiState.Success
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    val id = MutableLiveData("")
    val password = MutableLiveData("")
    val name = MutableLiveData("")
    val skill = MutableLiveData("")

    private val _signUpState = MutableLiveData<UiState>()
    val signUpState: LiveData<UiState>
        get() = _signUpState

    val isValidId: LiveData<Boolean> = id.map { id -> checkIdInvalid(id) }
    val isValidPassword: LiveData<Boolean> =
        password.map { password -> checkPasswordInvalid(password) }
    val isValidName: LiveData<Boolean> = name.map { name -> name.isNotEmpty() }
    val isValidSkill: LiveData<Boolean> = skill.map { skill -> skill.isNotEmpty() }

    fun onClickSignUp() {
        viewModelScope.launch {
            authRepository.signUp(
                RequestSignUpDto(
                    id.value.toString(),
                    password.value.toString(),
                    name.value.toString(),
                    skill.value.toString(),
                ),
            ).onSuccess {
                _signUpState.value = Success
            }.onFailure { throwable ->
                if (throwable is HttpException) {
                    when (throwable.code()) {
                        INVALID_INPUT -> _signUpState.value = Failure(INVALID_INPUT)
                        else -> _signUpState.value = Error
                    }
                }
            }
        }
    }

    private fun checkIdInvalid(id: String) = id.matches(ID_PATTERN)

    private fun checkPasswordInvalid(password: String) =
        password.matches(PASSWORD_PATTERN)

    companion object {
        val ID_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z0-9]{6,10}\$".toRegex()
        val PASSWORD_PATTERN =
            "^(?=.*[a-zA-Z])(?=.*[!@#\$%^&*()])(?=.*[0-9])[a-zA-Z!@#\$%^&*()0-9]{6,12}\$".toRegex()
    }
}
