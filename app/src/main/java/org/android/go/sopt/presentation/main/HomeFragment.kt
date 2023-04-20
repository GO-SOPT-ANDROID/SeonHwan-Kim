package org.android.go.sopt.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import org.android.go.sopt.R
import org.android.go.sopt.data.Dog
import org.android.go.sopt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗!_binding이 null이다!" }

    private val viewModel by viewModels<HomeViewModel>()

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
        dogAdapter.setDogList(viewModel.mockDogList)
        binding.rvHomeDog.adapter = ConcatAdapter(titleAdapter, dogAdapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun scrollToTop() {
        binding.rvHomeDog.smoothScrollToPosition(0)
    }
}