package com.funs.eye.ui.notification.push

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.funs.eye.Const
import com.funs.eye.logic.model.PushMessage
import com.funs.eye.logic.network.FunsEyeNetwork
import kotlinx.coroutines.flow.Flow

class PushViewModel: ViewModel() {

    var dataList = ArrayList<PushMessage.Message>()

    fun getPagingData(): Flow<PagingData<PushMessage.Message>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { PushPagingSource(FunsEyeNetwork.getInstance().mainPageService) }
        ).flow.cachedIn(viewModelScope)
    }
}
