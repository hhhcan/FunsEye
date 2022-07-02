package com.funs.eye.ui.home.daily

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.funs.eye.Const
import com.funs.eye.logic.model.Daily
import com.funs.eye.logic.network.FunsEyeNetwork
import kotlinx.coroutines.flow.Flow

class DailyViewModel : ViewModel() {

    var dataList = ArrayList<Daily.Item>()

    fun getPagingData(): Flow<PagingData<Daily.Item>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { DailyPagingSource(FunsEyeNetwork.getInstance().mainPageService) }
        ).flow.cachedIn(viewModelScope)
    }
}
