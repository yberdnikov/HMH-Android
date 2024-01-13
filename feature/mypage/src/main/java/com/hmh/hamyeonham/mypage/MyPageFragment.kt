package com.hmh.hamyeonham.mypage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import com.hmh.hamyeonham.common.BuildConfig
import com.hmh.hamyeonham.common.fragment.viewLifeCycle
import com.hmh.hamyeonham.common.fragment.viewLifeCycleScope
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.core.MainViewModel
import com.hmh.hamyeonham.feature.mypage.R
import com.hmh.hamyeonham.feature.mypage.databinding.FragmentMyPageBinding
import com.hmh.hamyeonham.userinfo.model.UserInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MyPageFragment : Fragment() {
    private val binding by viewBinding(FragmentMyPageBinding::bind)
    private val activityViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMyPageBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        collectMainState()
        initPrivacyButton()
        initTermOfUseButton()
    }

    private fun collectMainState() {
        activityViewModel.mainState.flowWithLifecycle(viewLifeCycle).onEach {
            bindMyPageWithUserInfo(it.userInfo)
        }.launchIn(viewLifeCycleScope)
    }

    private fun bindMyPageWithUserInfo(userInfo: UserInfo) {
        binding.tvUserName.text = userInfo.name
        binding.tvPoint.text = buildPointString(userInfo.point)
    }

    private fun buildPointString(point: Int): SpannableStringBuilder {
        val builder =
            SpannableStringBuilder(point.toString() + " " + getString(R.string.mypage_point_unit))
        builder.setSpan(
            ForegroundColorSpan(
                getColor(requireContext(), com.hmh.hamyeonham.core.designsystem.R.color.gray2)
            ),
            builder.length - 1,
            builder.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return builder
    }

    private fun initPrivacyButton() {
        binding.vPrivacy.setOnClickListener {
            val privacyRuleUrl = "https://www.notion.so/hmhteam/4219063a635e4eec8d8b9719c6c2a6fa"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(privacyRuleUrl))
            startActivity(intent)
        }
    }

    private fun initTermOfUseButton() {
        binding.vTermofuse.setOnClickListener {
            val termOfUseUrl = "https://www.notion.so/hmhteam/33acb29be57245f394eb93ddb2e3b8cc"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(termOfUseUrl))
            startActivity(intent)
        }
    }
}
