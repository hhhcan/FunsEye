package com.funs.eye.logic.network.api

import com.funs.eye.logic.model.CommunityRecommend
import com.funs.eye.logic.model.Follow
import com.funs.eye.logic.network.ServiceCreator
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * 主页界面，主要包含：（首页，社区，通知，我的）对应的 API 接口。
 *
 * @author can
 */
interface MainPageService {

    /**
     * 社区-推荐列表
     */
    @GET
    suspend fun getCommunityRecommend(@Url url: String): CommunityRecommend

    /**
     * 社区-关注列表
     */
    @GET
    suspend fun gethFollow(@Url url: String): Follow

    /**
     * 搜索-热搜关键词
     */
    @GET("api/v3/queries/hot")
    suspend fun getHotSearch(): List<String>


    companion object {

        /**
         * 社区-推荐列表
         */
        const val COMMUNITY_RECOMMEND_URL = "${ServiceCreator.BASE_URL}api/v7/community/tab/rec"

        /**
         * 社区-关注列表
         */
        const val FOLLOW_URL = "${ServiceCreator.BASE_URL}api/v6/community/tab/follow"

    }

}