package com.funs.eye.ui.home.discovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.funs.eye.Const
import com.funs.eye.logic.model.Discovery
import com.funs.eye.logic.network.FunsEyeNetwork
import kotlinx.coroutines.flow.Flow

class DiscoveryViewModel: ViewModel() {

    var dataList = ArrayList<Discovery.Item>()

    fun getPagingData(): Flow<PagingData<Discovery.Item>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { DiscoveryPagingSource(FunsEyeNetwork.getInstance().mainPageService) }
        ).flow.cachedIn(viewModelScope)
    }
}