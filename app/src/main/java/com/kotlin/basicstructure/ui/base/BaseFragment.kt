package com.kotlin.basicstructure.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.kotlin.basicstructure.util.PreferenceProvider
import com.kotlin.basicstructure.util.hideKeyboard

/**
 * Created by Sajid.
 */
abstract class BaseFragment : Fragment() {

    var mBaseActivity : BaseActivity? = null
    val mPreferenceProvider : PreferenceProvider?
        get() = mBaseActivity?.mPreferenceProvider

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity)
            mBaseActivity = context
    }

    override fun onDestroyView() {
        activity?.hideKeyboard()
        super.onDestroyView()
    }
}