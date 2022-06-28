package com.funs.eye.ui

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.funs.eye.R
import com.funs.eye.databinding.ActivityMainBinding
import com.funs.eye.extension.showToast
import com.funs.eye.ui.common.ui.BaseActivity
import com.funs.eye.util.GlobalUtil

class MainActivity : BaseActivity() {

    var _binding: ActivityMainBinding? = null

    val binding: ActivityMainBinding
        get() = _binding!!

    private var backPressTime = 0L

    private val fragmentManager: FragmentManager by lazy { supportFragmentManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            processBackPressed()
        }
    }

    private fun processBackPressed() {
        val now = System.currentTimeMillis()
        if (now - backPressTime > 2000) {
            String.format(GlobalUtil.getString(R.string.press_again_to_exit), GlobalUtil.appName)
                .showToast()
            backPressTime = now
        } else {
            super.onBackPressed()
        }
    }


}
