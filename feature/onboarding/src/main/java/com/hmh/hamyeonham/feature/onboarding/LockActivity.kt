package com.hmh.hamyeonham.feature.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.common.context.toast
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.databinding.ActivityLockBinding

class LockActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityLockBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLock.setOnClickListener {
            toast("유튜브 잠금 설정 완료")
        }
    }
}
