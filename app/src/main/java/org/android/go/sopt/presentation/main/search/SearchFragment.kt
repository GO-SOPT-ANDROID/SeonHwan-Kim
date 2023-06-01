package org.android.go.sopt.presentation.main.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentSearchBinding
import org.android.go.sopt.util.binding.BindingFragment
import org.android.go.sopt.util.extension.showShortToast
import org.android.go.sopt.util.state.UiState.Error
import org.android.go.sopt.util.state.UiState.Failure
import org.android.go.sopt.util.state.UiState.Success

@AndroidEntryPoint
class SearchFragment : BindingFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val viewModel by viewModels<SearchViewModel>()
    private var searchAdapter: SearchAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initAdapter()
        setupSearchList()
        setListEmpty()
    }

    private fun initAdapter() {
        searchAdapter = SearchAdapter()

        binding.rvSearchList.adapter = searchAdapter
    }

    private fun setupSearchList() {
        viewModel.searchState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Success -> searchAdapter?.submitList(viewModel.searchList.value)
                is Failure -> requireActivity().showShortToast("문제가 발생하였습니다")
                is Error -> requireActivity().showShortToast("문제가 발생하였습니다")
            }
        }
    }

    private fun setListEmpty() {
        binding.etSearchSearchVideo.doAfterTextChanged {
            if (it?.isNotBlank() == false) {
                searchAdapter?.submitList(null)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchAdapter = null
    }
}
