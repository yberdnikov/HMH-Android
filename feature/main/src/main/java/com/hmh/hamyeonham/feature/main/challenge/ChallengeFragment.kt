package com.hmh.hamyeonham.feature.main.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.main.databinding.FragmentChallengeBinding

class ChallengeFragment : Fragment() {

    private val binding by viewBinding(FragmentChallengeBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentChallengeBinding.inflate(inflater, container, false).root
    }
}
