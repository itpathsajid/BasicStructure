package com.kotlin.basicstructure.ui.main.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.basicstructure.R
import com.kotlin.basicstructure.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListener()
    }

    private fun setClickListener() {
        binding.btnSignUp.setOnClickListener(this)
        binding.signin.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnSignUp -> {
                doSignUp()
            }
            R.id.signin -> {
                goToSignIn()
            }
        }
    }

    private fun goToSignIn() {
       onBackPressed()
    }

    private fun doSignUp() {
        when {
            binding.etName.text.toString().isEmpty() -> {
                binding.etName.error = getString(R.string.please_enter_name)
                binding.etName.requestFocus()
            }
            binding.etEmail.text.toString().isEmpty() -> {
                binding.etEmail.error = getString(R.string.please_enter_email)
                binding. etEmail.requestFocus()
            }
            binding.etMobile.text.toString().isEmpty() -> {
                binding.etMobile.error = getString(R.string.please_enter_mobile_number)
                binding.etMobile.requestFocus()
            }
            binding.etMobile.text.toString().length < 10 -> {
                binding.etMobile.error = getString(R.string.please_enter_valid_mobile_number)
                binding.etMobile.requestFocus()
            }
            binding.etPassword.text.toString().isEmpty() -> {
                binding.etPassword.error = getString(R.string.please_enter_password)
                binding.etPassword.requestFocus()
            }

            binding.etConfirmPassword.text.toString() != binding.etPassword.text.toString() -> {
                binding.etConfirmPassword.error = getString(R.string.confirm_password_not_match)
                binding.etConfirmPassword.requestFocus()
            }
            else -> {
               //API call for sign up
            }
        }
    }

}