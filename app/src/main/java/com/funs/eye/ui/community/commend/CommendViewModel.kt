package com.funs.eye.ui.community.commend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.funs.eye.Const
import com.funs.eye.logic.model.CommunityRecommend
import com.funs.eye.logic.network.FunsEyeNetwork
import kotlinx.coroutines.flow.Flow

class CommendViewModel : ViewModel() {

    fun getPagingData(): Flow<PagingData<CommunityRecommend.Item>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { CommendPagingSource(FunsEyeNetwork.getInstance().mainPageService) }
        ).flow.cachedIn(viewModelScope)
    }
}