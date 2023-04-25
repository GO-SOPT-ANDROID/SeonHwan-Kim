package org.android.go.sopt.presentation.main.mypage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentMyPageBinding
import org.android.go.sopt.presentation.login.LoginActivity

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding: FragmentMyPageBinding
        get() = requireNotNull(_binding) { "binding이 null이다!" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.logout()
        this.withdrawal()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun logout() {
        binding.btMyPageLogout.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            val sharedPreferences = this.requireActivity().getSharedPreferences(KEY_PREFS, 0)
            sharedPreferences.edit().putBoolean(KEY_ISLOGIN, false).apply()
            startActivity(intent)
            finishFragment()
        }
    }

    private fun withdrawal() {
        binding.btMyPageWithdrawal.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            val sharedPreferences = this.requireActivity().getSharedPreferences(KEY_PREFS, 0)
            sharedPreferences.edit().clear().apply()
            startActivity(intent)
            finishFragment()
        }
    }

    private fun finishFragment(){
        if(!this.requireActivity().isFinishing) this.requireActivity().finish()
    }

    companion object {
        private const val KEY_PREFS = "userInfo"
        private const val KEY_ISLOGIN = "isLogin"
    }
}