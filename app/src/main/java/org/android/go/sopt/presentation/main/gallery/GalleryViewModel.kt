package org.android.go.sopt.presentation.main.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.go.sopt.domain.entity.HomeUser
import org.android.go.sopt.domain.repository.HomeUserReponsitory
import org.android.go.sopt.util.state.UiState
import org.android.go.sopt.util.state.UiState.Failure
import org.android.go.sopt.util.state.UiState.Success
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val homeUserReponsitory: HomeUserReponsitory,
) : ViewModel() {
    private val _userList = MutableLiveData<List<HomeUser>>()
    val userList: LiveData<List<HomeUser>>
        get() = _userList

    private val _userListState = MutableLiveData<UiState>()
    val userListState: LiveData<UiState>
        get() = _userListState

    init {
        getUserList()
    }

    private fun getUserList() {
        viewModelScope.launch {
            homeUserReponsitory.getHomeUser().onSuccess { response ->
                _userList.value = response
                _userListState.value = Success
            }.onFailure { throwable ->
                if (throwable is HttpException) {
                    _userListState.value = Failure(throwable.code())
                }
            }
        }
    }
}
