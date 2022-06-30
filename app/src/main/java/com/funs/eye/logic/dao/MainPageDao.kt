package com.funs.eye.logic.dao

import com.funs.eye.logic.model.CommunityRecommend
import com.funs.eye.logic.model.Daily
import com.funs.eye.logic.model.Discovery
import com.funs.eye.logic.model.Follow

/**
 * 主页界面（主要包含：首页，社区，通知，我的），对应的Dao操作类。
 */
class MainPageDao {

    /*----------------------------首页相关----------------------------*/

    fun cacheDiscovery(bean: Discovery?) {
        //TODO("存储数据到本地")
    }

    fun getCachedDiscovery(): Discovery? {
        TODO("获取本地存储的数据")
    }

    fun cacheDaily(bean: Daily?) {
        //TODO("存储数据到本地")
    }

    fun getCachedDaily(): Daily? {
        TODO("获取本地存储的数据")
    }

    /*----------------------------社区相关----------------------------*/

    fun cacheCommunityRecommend(bean: CommunityRecommend?) {
        //TODO("存储数据到本地")
    }

    fun getCachedCommunityRecommend(): CommunityRecommend? {
        TODO("获取本地存储的数据")
    }

    fun cacheFollow(bean: Follow?) {
        //TODO("存储数据到本地")
    }

    fun getCachedFollow(): Follow? {
        TODO("获取本地存储的数据")
    }

    /*----------------------------通知相关----------------------------*/

    /*----------------------------搜索相关----------------------------*/

    fun cacheHotSearch(bean: List<String>?) {
        //TODO("存储数据到本地")
    }

    fun getHotSearch(): List<String>? {
        TODO("获取本地存储的数据")
    }

}