package com.hmh.hamyeonham.feature.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.core.MainViewModel
import com.hmh.hamyeonham.feature.mypage.databinding.FragmentMyPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        bindViewWithUserInfo()
//        collectAndBindUserInfo()
    }

//    private fun collectAndBindUserInfo() {
//        lifecycleScope.launch {
//            mainViewModel.userInfo.collect {
//                bindViewWithUserInfo(it)
//            }
//        }
//    }

    private fun bindViewWithUserInfo() {
        binding.tvMypageName.text = "여민서"
        binding.tvMypagePoint.text = "0 P"
    }
}
