package com.funs.eye.ui

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.funs.eye.R
import com.funs.eye.databinding.ActivityMainBinding
import com.funs.eye.event.RefreshEvent
import com.funs.eye.extension.setOnClickListener
import com.funs.eye.extension.showToast
import com.funs.eye.ui.common.ui.BaseActivity
import com.funs.eye.ui.community.CommunityFragment
import com.funs.eye.util.GlobalUtil
import org.greenrobot.eventbus.EventBus

class MainActivity : BaseActivity() {

    var _binding: ActivityMainBinding? = null

    val binding: ActivityMainBinding
        get() = _binding!!

    private var backPressTime = 0L

    private var communityFragment: CommunityFragment? = null

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

    override fun setupViews() {
        setOnClickListener(binding.navigationBar.btnCommunity) {
            when (this) {
                binding.navigationBar.btnCommunity -> {
                    notificationUiRefresh(1)
                    setTabSelection(1)
                }
            }
        }
        setTabSelection(0)
    }

    private fun setTabSelection(index: Int) {
        clearAllSelected()
        fragmentManager.beginTransaction().apply {
            hideFragments(this)
            when (index) {
                1 -> {
                    binding.navigationBar.ivCommunity.isSelected = true
                    binding.navigationBar.tvCommunity.isSelected = true
                    if (communityFragment == null) {
                        communityFragment = CommunityFragment()
                        add(R.id.homeActivityFragContainer, communityFragment!!)
                    } else {
                        show(communityFragment!!)
                    }
                }
            }
        }.commitAllowingStateLoss()
    }

    private fun clearAllSelected() {
        binding.navigationBar.ivHomePage.isSelected = false
        binding.navigationBar.tvHomePage.isSelected = false
        binding.navigationBar.ivCommunity.isSelected = false
        binding.navigationBar.tvCommunity.isSelected = false
        binding.navigationBar.ivNotification.isSelected = false
        binding.navigationBar.tvNotification.isSelected = false
        binding.navigationBar.ivMine.isSelected = false
        binding.navigationBar.tvMine.isSelected = false
    }

    private fun hideFragments(transaction: FragmentTransaction) {
        transaction.run {
            if (communityFragment != null) hide(communityFragment!!)
        }
    }

    private fun notificationUiRefresh(selectionIndex: Int) {
        when (selectionIndex) {
            1 -> {
                if (binding.navigationBar.ivCommunity.isSelected)
                    EventBus.getDefault().post(RefreshEvent(CommunityFragment::class.java)
                )
            }
        }
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
