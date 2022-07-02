package com.funs.eye.logic.network.api

import com.funs.eye.logic.model.*
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
     * 首页-发现列表
     */
    @GET
    suspend fun getDiscovery(@Url url: String): Discovery

    /**
     * 首页-日报列表
     */
    @GET
    suspend fun getDaily(@Url url: String): Daily

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
     * 通知-推送列表
     */
    @GET
    suspend fun getPushMessage(@Url url: String): PushMessage

    companion object {

        /**
         * 首页-发现列表
         */
        const val DISCOVERY_URL = "${ServiceCreator.BASE_URL}api/v7/index/tab/discovery"

        /**
         * 首页-日报列表
         */
        const val DAILY_URL = "${ServiceCreator.BASE_URL}api/v5/index/tab/feed"

        /**
         * 社区-推荐列表
         */
        const val COMMUNITY_RECOMMEND_URL = "${ServiceCreator.BASE_URL}api/v7/community/tab/rec"

        /**
         * 社区-关注列表
         */
        const val FOLLOW_URL = "${ServiceCreator.BASE_URL}api/v6/community/tab/follow"

        /**
         * 通知-推送列表
         */
        const val PUSHMESSAGE_URL = "${ServiceCreator.BASE_URL}api/v3/messages"

    }

}