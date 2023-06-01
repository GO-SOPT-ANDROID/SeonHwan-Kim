package org.android.go.sopt.presentation.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.go.sopt.domain.entity.KakaoSearch
import org.android.go.sopt.domain.repository.KakaoSearchRepository
import org.android.go.sopt.util.state.UiState
import org.android.go.sopt.util.state.UiState.Failure
import org.android.go.sopt.util.state.UiState.Success
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: KakaoSearchRepository,
) : ViewModel() {
    val query = MutableLiveData("")

    private val _searchList = MutableLiveData<List<KakaoSearch>>()
    val searchList: LiveData<List<KakaoSearch>>
        get() = _searchList

    private val _searchState = MutableLiveData<UiState>()
    val searchState: LiveData<UiState>
        get() = _searchState

    fun onClickSearchButton() {
        viewModelScope.launch {
            searchRepository.kakaoSearch(query.value.toString())
                .onSuccess { response ->
                    _searchList.value = response
                    _searchState.value = Success
                }
                .onFailure { throwable ->
                    Timber.d(throwable)
                    _searchState.value = Failure(null)
                }
        }
    }
}
