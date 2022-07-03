package com.funs.eye.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.funs.eye.R
import com.funs.eye.databinding.FragmentMineBinding
import com.funs.eye.extension.setOnClickListener
import com.funs.eye.extension.setTextViewUnderline
import com.funs.eye.ui.common.ui.BaseFragment
import com.funs.eye.ui.common.ui.WebViewActivity
import com.funs.eye.ui.common.ui.WebViewActivity.Companion.MODE_SONIC_WITH_OFFLINE_CACHE
import com.funs.eye.util.GlobalUtil

/**
 * 我的界面
 *
 */
class MineFragment : BaseFragment() {

    var _binding: FragmentMineBinding? = null

    val binding: FragmentMineBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMineBinding.inflate(inflater, container, false)
        return super.onCreateView(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.collapsingToolbarLayout.title = GlobalUtil.appName
        binding.collapsingToolbarLayout.setCollapsedTitleTextColor(
            ContextCompat.getColor(
                activity,
                R.color.black
            )
        )
        binding.tvBlog.setTextViewUnderline(GlobalUtil.getString(R.string.blog));
        binding.tvGithub.setTextViewUnderline(GlobalUtil.getString(R.string.github));

        setOnClickListener(
            binding.tvBlog, binding.tvGithub
        ) {
            when (this) {

                binding.tvBlog -> {
                    WebViewActivity.start(
                        activity,
                        WebViewActivity.DEFAULT_TITLE,
                        "https://blog.csdn.net/jb_home",
                        false,
                        false,
                        MODE_SONIC_WITH_OFFLINE_CACHE
                    )
                }
                binding.tvGithub -> {
                    WebViewActivity.start(
                        activity,
                        WebViewActivity.DEFAULT_TITLE,
                        "https://github.com/hhhcan",
                        false,
                        false,
                        MODE_SONIC_WITH_OFFLINE_CACHE
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = MineFragment()
    }
}
