package org.android.go.sopt.presentation.main.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.model.ResponseKakaoSearchDto
import org.android.go.sopt.databinding.FragmentSearchBinding
import org.android.go.sopt.util.showShortToast
import retrofit2.Call
import retrofit2.Response

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = requireNotNull(_binding) { "앗! binding이 null이다!" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.onClickSearchBtn()
        this.setListEmpty()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onClickSearchBtn() {
        binding.ibSearchBtn.setOnClickListener {
            ServicePool.kakaoSearchService.kakaoSearchVideo(
                query = binding.etSearchSearchVideo.text.toString(),
                sort = "accuracy",
                page = 1,
                size = 30,
            ).enqueue(object : retrofit2.Callback<ResponseKakaoSearchDto> {
                override fun onResponse(
                    call: Call<ResponseKakaoSearchDto>,
                    response: Response<ResponseKakaoSearchDto>
                ) {
                    if (response.isSuccessful) {
                        binding.rvSearchList.adapter = SearchAdapter(requireContext()).apply {
                            submitList(response.body()?.documents)
                        }
                    } else {
                        requireActivity().showShortToast("실패")
                    }
                }

                override fun onFailure(call: Call<ResponseKakaoSearchDto>, t: Throwable) {
                    requireActivity().showShortToast(t.message.toString())
                    Log.d("------------------------------\n", t.message.toString())
                }

            })
        }
    }

    private fun setListEmpty() {
        binding.etSearchSearchVideo.doAfterTextChanged {
            if (it?.isNotBlank() == false) {
                binding.rvSearchList.adapter = SearchAdapter(requireContext()).apply {
                    submitList(null)
                }
            }
        }
    }
}