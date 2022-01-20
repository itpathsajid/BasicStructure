package com.kotlin.basicstructure.ui.main.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.kotlin.basicstructure.R
import com.kotlin.basicstructure.databinding.ActivityLoginBinding
import com.kotlin.basicstructure.ui.base.BaseActivity
import com.kotlin.basicstructure.ui.main.viewmodel.LoginViewModel


class LoginActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding : ActivityLoginBinding
    private val loginViewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.viewModel = loginViewModel
        setContentView(binding.root)
        setClickListener()
        setupViewModel()
    }

    private fun setClickListener() {
        binding.btnLogin.setOnClickListener(this)
        binding.signup.setOnClickListener(this)
    }

    private fun setupViewModel() {
        //Init ViewModel Here
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnLogin -> {
                doLogin()
            }
            R.id.signup -> {
                goToSignUp()
            }
        }
    }

    private fun goToSignUp() {
        val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun doLogin() {
        when {
            binding.etMobile.text.toString().isEmpty() -> {
                binding.etMobile.error = getString(R.string.please_enter_mobile_number)
            }
            binding.etMobile.text.toString().length < 10 -> {
                binding.etMobile.error = getString(R.string.please_enter_valid_mobile_number)
            }
            binding.etPassword.text.toString().isEmpty() -> {
                binding.etMobile.error = getString(R.string.please_enter_password)
            }
            else -> {
                //API call for login
            }
        }
    }

}