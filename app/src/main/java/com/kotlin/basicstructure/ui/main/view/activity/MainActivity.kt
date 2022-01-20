package com.kotlin.basicstructure.ui.main.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.basicstructure.R
import com.kotlin.basicstructure.data.model.Photo
import com.kotlin.basicstructure.data.model.UserResponse
import com.kotlin.basicstructure.data.source.network.NetworkResult
import com.kotlin.basicstructure.databinding.ActivityMainBinding
import com.kotlin.basicstructure.ui.base.BaseActivity
import com.kotlin.basicstructure.ui.main.adapter.UserAdapter
import com.kotlin.basicstructure.ui.main.viewmodel.HomeViewModel
import com.kotlin.basicstructure.util.Constants.PERMISSION_REQUEST_CODE
import com.kotlin.basicstructure.util.PermissionHelper
import com.kotlin.basicstructure.util.hide
import com.kotlin.basicstructure.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), PermissionHelper.PermissionCallback {
    private var loadMore: Boolean = false
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter
    private var photos: ArrayList<Photo> = ArrayList()
    private lateinit var binding: ActivityMainBinding
    private var permissionHelper: PermissionHelper? = null
    private var permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.CAMERA)

    private var adapterClickCallbacks = object : UserAdapter.ClickListener {
        override fun itemClick(position: Int) {
            Toast.makeText(this@MainActivity, "$position is clicked", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MyLearningProject)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObserver()
        setClickListener()
        setLoadMoreListener()
        getUserData()
        permissionHelper = PermissionHelper(this, permissions, PERMISSION_REQUEST_CODE)
        if (hasPermissions() == true){
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Permission not granted yet", Toast.LENGTH_SHORT).show()
            requestPermission()
        }

        binding.endlessList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        userAdapter = UserAdapter(photos, adapterClickCallbacks)
        binding.endlessList.adapter = userAdapter
    }


    private fun setLoadMoreListener() {

        binding.endlessList.setEndlessScrollCallback {
            //Now list view can be set so that it will block load until certain task finish
//            endless_list.blockLoading()
            loadMore = true
            homeViewModel.updatePage()
            homeViewModel.fetchUserData()
        }
    }

    private fun setClickListener() {
    }

    private fun setUpObserver() {
        homeViewModel.userLiveData.observe(this,
            {
                if (it != null) {
                    handleUserData(it)
                }
            })
    }

    private fun handleUserData(result: NetworkResult<UserResponse>) {
        when (result) {
            is NetworkResult.Loading -> {
                // show a progress bar
                Log.e("TAG", "handleUserData() --> Loading  $result")
                binding.progressBarMain.show()
            }
            is NetworkResult.Success -> {
                // bind data to the view
                Log.e("TAG", "handleUserData() --> Success  $result")
                binding.progressBarMain.hide()
                result.data?.let { userAdapter.setData(it.photos, loadMore) }
            }
            is NetworkResult.Error -> {
                // show error message
                Log.e("TAG", "handleUserData() --> Error ${result.message}")
                binding.progressBarMain.hide()
            }
        }
    }

    private fun getUserData() {
        homeViewModel.fetchUserData()
    }

    private fun hasPermissions() =
        permissionHelper?.checkSelfPermission(permissions)

    private fun requestPermission() {
        permissionHelper?.request(this)
    }

    override fun onPermissionGranted() {
        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionDenied(permissionDeniedError: String) {
        Toast.makeText(this, "Location Permission is required for this app", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionDeniedBySystem(permissionDeniedBySystem: String) {
        Toast.makeText(this, "Permission Denied Permanently send user to settings ", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionHelper?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
