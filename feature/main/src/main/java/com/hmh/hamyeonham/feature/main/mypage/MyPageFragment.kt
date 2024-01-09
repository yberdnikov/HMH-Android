package com.hmh.hamyeonham.feature.main.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.main.MainViewModel
import com.hmh.hamyeonham.feature.main.databinding.FragmentMyPageBinding
import com.hmh.hamyeonham.userinfo.model.UserInfo

class MyPageFragment : Fragment() {
    private val binding by viewBinding(FragmentMyPageBinding::bind)
    private val mainViewModel by activityViewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentMyPageBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewWithUserInfo(mainViewModel.userInfo.value)
//        collectAndBindUserInfo()
    }

//    private fun collectAndBindUserInfo() {
//        lifecycleScope.launch {
//            mainViewModel.userInfo.collect {
//                bindViewWithUserInfo(it)
//            }
//        }
//    }

    private fun bindViewWithUserInfo(userInfo: UserInfo) {
        binding.tvMypageName.text = userInfo.name
        binding.tvMypagePoint.text = userInfo.point.toString()
    }
}
