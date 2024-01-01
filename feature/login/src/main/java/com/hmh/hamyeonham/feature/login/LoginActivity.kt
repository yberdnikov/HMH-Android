package com.hmh.hamyeonham.feature.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hmh.hamyeonham.feature.login.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}