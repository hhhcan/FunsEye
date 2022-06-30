package com.funs.eye.logic.dao

/**
 * 应用程序所有Dao操作管理类。
 *
 */
object EyepetizerDatabase {

    private var mainPageDao: MainPageDao? = null

    private var videoDao: VideoDao? = null

    fun getMainPageDao(): MainPageDao {
        if (mainPageDao == null) {
            mainPageDao = MainPageDao()
        }
        return mainPageDao!!
    }

    fun getVideoDao(): VideoDao {
        if (videoDao == null) {
            videoDao = VideoDao()
        }
        return videoDao!!
    }
}