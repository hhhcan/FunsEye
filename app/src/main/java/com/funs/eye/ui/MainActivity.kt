package com.funs.eye.ui

import android.os.Bundle
import com.funs.eye.databinding.ActivityMainBinding
import com.funs.eye.ui.common.ui.BaseActivity

class MainActivity : BaseActivity() {

    var _binding: ActivityMainBinding? = null

    val binding: ActivityMainBinding
        get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }
}
