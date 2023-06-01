package org.android.go.sopt.presentation.main.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentGalleryBinding
import org.android.go.sopt.util.binding.BindingFragment
import org.android.go.sopt.util.extension.showShortToast
import org.android.go.sopt.util.state.UiState.Error
import org.android.go.sopt.util.state.UiState.Failure
import org.android.go.sopt.util.state.UiState.Success

@AndroidEntryPoint
class GalleryFragment : BindingFragment<FragmentGalleryBinding>(R.layout.fragment_gallery) {
    private val viewModel by viewModels<GalleryViewModel>()
    private var pagerAdapter: PagerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        getUserData()
    }

    private fun initAdapter() {
        pagerAdapter = PagerAdapter()

        binding.pagerGallery.adapter = pagerAdapter
    }

    private fun getUserData() {
        viewModel.userListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Success -> {
                    pagerAdapter?.submitList(viewModel.userList.value)
                }

                is Failure -> {
                    requireActivity().showShortToast("failure")
                }

                is Error -> {
                    requireActivity().showShortToast("error")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        pagerAdapter = null
    }
}
