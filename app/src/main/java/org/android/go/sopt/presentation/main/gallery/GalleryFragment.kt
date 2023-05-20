package org.android.go.sopt.presentation.main.gallery

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.model.ResponseHomeUserDto
import org.android.go.sopt.databinding.FragmentGalleryBinding
import org.android.go.sopt.util.showShortToast
import retrofit2.Call
import retrofit2.Response

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding) { "앗!_binding이 null이다!" }

    private val homeUserService = ServicePool.getHomeUserService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.getUserData()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun getUserData() {
        homeUserService.GetHomeUserData().enqueue(object : retrofit2.Callback<ResponseHomeUserDto> {
            override fun onResponse(
                call: Call<ResponseHomeUserDto>,
                response: Response<ResponseHomeUserDto>
            ) {
                if (response.isSuccessful) {
                    Log.d("---------------\n", response.body()?.data.toString())
                    binding.pagerGallery.adapter = PagerAdapter().apply {
                        submitList(response.body()?.data)
                    }
                    requireActivity().showShortToast("성공")
                } else {
                    Log.d("----------------------\n", response.message())
                    requireActivity().showShortToast("실패")
                }
            }

            override fun onFailure(call: Call<ResponseHomeUserDto>, t: Throwable) {
                Log.d("-----------------------\n", t.toString())
            }
        })
    }
}