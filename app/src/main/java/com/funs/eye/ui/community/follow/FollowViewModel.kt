package com.funs.eye.ui.community.follow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.funs.eye.Const
import com.funs.eye.logic.model.Follow
import com.funs.eye.logic.network.FunsEyeNetwork
import kotlinx.coroutines.flow.Flow

class FollowViewModel: ViewModel() {

    var dataList = ArrayList<Follow.Item>()

    fun getPagingData(): Flow<PagingData<Follow.Item>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { FollowPagingSource(FunsEyeNetwork.getInstance().mainPageService) }
        ).flow.cachedIn(viewModelScope)

    }
}