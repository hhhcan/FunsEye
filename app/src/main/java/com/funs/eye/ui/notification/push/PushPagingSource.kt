package com.funs.eye.ui.notification.push

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.funs.eye.logic.model.PushMessage
import com.funs.eye.logic.network.api.MainPageService

class PushPagingSource(private val mainPageService: MainPageService) : PagingSource<String, PushMessage.Message>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PushMessage.Message> {
        return try {
            val page = params.key ?: MainPageService.PUSHMESSAGE_URL
            val repoResponse = mainPageService.getPushMessage(page)
            val repoItems = repoResponse.itemList
            val prevKey = null
            val nextKey = if (repoItems.isNotEmpty() && !repoResponse.nextPageUrl.isNullOrEmpty()) repoResponse.nextPageUrl else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, PushMessage.Message>): String? = null
}