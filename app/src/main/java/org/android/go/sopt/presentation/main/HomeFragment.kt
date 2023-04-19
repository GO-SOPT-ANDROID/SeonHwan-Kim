package org.android.go.sopt.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import org.android.go.sopt.R
import org.android.go.sopt.data.Dog
import org.android.go.sopt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗!_binding이 null이다!" }

    private val mockDogList = listOf<Dog>(
        Dog("포메라니안", R.drawable.img_pomeranian, SIZE_SMALL),
        Dog("말티즈", R.drawable.img_maltese, SIZE_SMALL),
        Dog("토이 푸들", R.drawable.img_toy_poodle, SIZE_SMALL),
        Dog("웰시코기", R.drawable.img_welshi_corgi, SIZE_MEDIUM),
        Dog("진돗개", R.drawable.img_jindo, SIZE_MEDIUM),
        Dog("비글", R.drawable.img_beagle, SIZE_MEDIUM),
        Dog("골든 리트리버", R.drawable.img_golden_retriever, SIZE_LARGE),
        Dog("사모예드", R.drawable.img_samoyed, SIZE_LARGE),
        Dog("셰퍼드", R.drawable.img_shepherd, SIZE_LARGE)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleAdapter = TitleAdapter(requireContext())
        val dogAdapter = DogAdapter(requireContext())
        dogAdapter.setDogList(mockDogList)
        binding.rvHomeDog.adapter = ConcatAdapter(titleAdapter, dogAdapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val SIZE_SMALL = "소형견"
        private const val SIZE_MEDIUM = "중형견"
        private const val SIZE_LARGE = "대형견"
    }
}