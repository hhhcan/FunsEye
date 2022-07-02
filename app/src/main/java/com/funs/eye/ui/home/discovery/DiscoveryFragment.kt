package com.funs.eye.ui.home.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.funs.eye.R
import com.funs.eye.databinding.FragmentRefreshLayoutBinding
import com.funs.eye.event.MessageEvent
import com.funs.eye.event.RefreshEvent
import com.funs.eye.extension.showToast
import com.funs.eye.ui.common.ui.BaseFragment
import com.funs.eye.ui.common.ui.FooterAdapter
import com.funs.eye.util.GlobalUtil
import com.funs.eye.util.ResponseHandler
import com.scwang.smart.refresh.layout.constant.RefreshState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * 首页-发现列表界面。
 *
 */
class DiscoveryFragment : BaseFragment() {

    private var _binding: FragmentRefreshLayoutBinding? = null

    private val binding
        get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(DiscoveryViewModel::class.java)
    }

    private lateinit var adapter: DiscoveryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRefreshLayoutBinding.inflate(layoutInflater, container, false)
        return super.onCreateView(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DiscoveryAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter =
            adapter.withLoadStateFooter(FooterAdapter { adapter.retry() })
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.itemAnimator = null
        binding.refreshLayout.setOnRefreshListener {
            adapter.refresh()
        }

        addLoadStateListener()

        lifecycleScope.launch {
            viewModel.getPagingData().collect {
                adapter.submitData(it)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun loadFinished() {
        super.loadFinished()
        binding.refreshLayout.finishRefresh()
    }

    override fun loadFailed(msg: String?) {
        super.loadFailed(msg)
        binding.refreshLayout.finishRefresh()
        showLoadErrorView(msg ?: GlobalUtil.getString(R.string.unknown_error)) {
            startLoading()
            adapter.refresh()
        }
    }

    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)
        if (messageEvent is RefreshEvent && javaClass == messageEvent.activityClass) {
            binding.refreshLayout.autoRefresh()
            if (binding.recyclerView.adapter?.itemCount ?: 0 > 0) {
                binding.recyclerView.scrollToPosition(0)
            }
        }
    }

    private fun addLoadStateListener() {
        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    loadFinished()
                    if (it.source.append.endOfPaginationReached) {
                        binding.refreshLayout.setEnableLoadMore(true)
                        binding.refreshLayout.finishLoadMoreWithNoMoreData()
                    } else {
                        binding.refreshLayout.setEnableLoadMore(false)
                    }
                }
                is LoadState.Loading -> {
                    if (binding.refreshLayout.state != RefreshState.Refreshing) {
                        startLoading()
                    }
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    loadFailed(ResponseHandler.getFailureTips(state.error))
                }
            }
        }

        adapter.addLoadStateListener {
            when (it.append) {
                is LoadState.NotLoading -> {
                    if (it.source.append.endOfPaginationReached) {
                        binding.refreshLayout.setEnableLoadMore(true)
                        binding.refreshLayout.finishLoadMoreWithNoMoreData()
                    } else {
                        binding.refreshLayout.setEnableLoadMore(false)
                    }
                }
                is LoadState.Loading -> {

                }
                is LoadState.Error -> {
                    val state = it.append as LoadState.Error
                    ResponseHandler.getFailureTips(state.error).showToast()
                }
            }
        }
    }


    companion object {

        fun newInstance() = DiscoveryFragment()
    }

}
