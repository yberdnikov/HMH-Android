package com.hmh.hamyeonham.challenge.appselection.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.databinding.FrargmentAppSelectionBinding

class AppSelectionFragment:Fragment() {
    private val binding by viewBinding(FrargmentAppSelectionBinding::bind)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FrargmentAppSelectionBinding.inflate(inflater,container,false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
