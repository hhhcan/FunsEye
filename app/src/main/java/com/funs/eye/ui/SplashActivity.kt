package com.funs.eye.ui

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.lifecycle.lifecycleScope
import com.funs.eye.R
import com.funs.eye.databinding.ActivitySplashBinding
import com.funs.eye.extension.logW
import com.funs.eye.ui.common.ui.BaseActivity
import com.funs.eye.util.GlobalUtil
import com.permissionx.guolindev.PermissionX
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {

    var _binding: ActivitySplashBinding? = null

    val binding: ActivitySplashBinding
        get() = _binding!!

    private val splashDuration = 3 * 1000L

    private val scaleAnimation by lazy {
        ScaleAnimation(
            1f, 1.05f,
            1f, 1.05f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
            .apply {
                duration = splashDuration
                fillAfter = true
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWriteExternalStoragePermission()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }

    override fun setupViews() {
        super.setupViews()
        binding.ivSplashPicture.startAnimation(scaleAnimation)
        logW("lifecycleScope1", Thread.currentThread().name)
        lifecycleScope.launch {
            logW("lifecycleScope2", Thread.currentThread().name)
            delay(splashDuration)
            logW("lifecycleScope4", Thread.currentThread().name)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
        logW("lifecycleScope3", Thread.currentThread().name)
    }

    private fun requestWriteExternalStoragePermission() {

        PermissionX.init(this@SplashActivity)
            .permissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .onExplainRequestReason { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_picture_processing)
                scope.showRequestReasonDialog(
                    deniedList,
                    message,
                    GlobalUtil.getString(R.string.ok),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .onForwardToSettings { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_picture_processing)
                scope.showForwardToSettingsDialog(
                    deniedList,
                    message,
                    GlobalUtil.getString(R.string.settings),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .request { allGranted, grantedList, deniedList ->
                requestReadPhoneStatePermission()
            }
    }

    private fun requestReadPhoneStatePermission() {
        PermissionX.init(this@SplashActivity)
            .permissions(android.Manifest.permission.READ_PHONE_STATE)
            .onExplainRequestReason { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_access_phone_info)
                scope.showRequestReasonDialog(
                    deniedList,
                    message,
                    GlobalUtil.getString(R.string.ok),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .onForwardToSettings { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_access_phone_info)
                scope.showForwardToSettingsDialog(
                    deniedList,
                    message,
                    GlobalUtil.getString(R.string.settings),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .request { allGranted, grantedList, deniedList ->
                _binding = ActivitySplashBinding.inflate(layoutInflater)
                setContentView(binding.root)
            }
    }

}