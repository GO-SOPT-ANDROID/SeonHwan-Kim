package org.android.go.sopt.presentation.main.mypage

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.android.go.sopt.databinding.FragmentMyPageBinding
import org.android.go.sopt.SoptApplication
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.util.showShortToast

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

        binding.tvMyPageName.text = "이름 : ${SoptApplication.prefs.getString(KEY_NAME, "")}"
        binding.tvMyPageSpecialty.text =
            "특기 : ${SoptApplication.prefs.getString(KEY_SPECIALTY, "")}"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun logout() {
        binding.btMyPageLogout.setOnClickListener {
            AlertDialog.Builder(context).setMessage("정말 로그아웃하시겠습니까?")
                .setPositiveButton(
                    "네"
                ) { dialog, which ->
                    val intent = Intent(activity, LoginActivity::class.java)
                    SoptApplication.prefs.setBoolean(KEY_ISLOGIN, false)
                    startActivity(intent)
                    requireActivity().showShortToast("로그아웃되었습니다.")
                    finishFragment()
                }.setNegativeButton("아니요") { dialog, which ->

                }.show()
        }
    }

    private fun withdrawal() {
        binding.btMyPageWithdrawal.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            AlertDialog.Builder(context).setMessage("정말 회원탈퇴를 하시겠습니까?")
                .setPositiveButton(
                    "네"
                ) { dialog, which ->
                    requireActivity().showShortToast(
                        "${
                            SoptApplication.prefs.getString(
                                KEY_NAME,
                                ""
                            )
                        }님 회원탈퇴되었습니다."
                    )
                    val intent = Intent(activity, LoginActivity::class.java)
                    SoptApplication.prefs.deleteUserInformation()
                    startActivity(intent)
                    finishFragment()
                }.setNegativeButton("아니요") { dialog, which ->
                    requireActivity().showShortToast("감사합니다")
                }.show()
        }
    }

    private fun finishFragment() {
        if (!this.requireActivity().isFinishing) this.requireActivity().finish()
    }

    companion object {
        private const val KEY_ISLOGIN = "isLogin"
        private const val KEY_NAME = "name"
        private const val KEY_SPECIALTY = "specialty"
    }
}