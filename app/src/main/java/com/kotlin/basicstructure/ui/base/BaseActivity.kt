package com.kotlin.basicstructure.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.basicstructure.util.PreferenceProvider
import com.kotlin.basicstructure.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Sajid.
 */

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity(){

    @Inject
    lateinit var mPreferenceProvider: PreferenceProvider

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onDestroy() {
        hideKeyboard()
        super.onDestroy()
    }
}