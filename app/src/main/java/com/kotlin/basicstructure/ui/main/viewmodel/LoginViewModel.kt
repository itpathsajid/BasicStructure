package com.kotlin.basicstructure.ui.main.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kotlin.basicstructure.R
import com.kotlin.basicstructure.util.isEmailValid
import com.kotlin.basicstructure.util.isPasswordValid
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    lateinit var errorMessages : MutableLiveData<String>


    fun isValidEmailAndPassword(context: Context) : Boolean {
        return when {
            !email.value.isEmailValid() -> {
                errorMessages.value = context.getString(R.string.invalid_email)
                false
            }
            !password.value.isPasswordValid() -> {
                errorMessages.value = context.getString(R.string.invalid_password)
                false
            }
            else -> {
                true
            }
        }
    }

}