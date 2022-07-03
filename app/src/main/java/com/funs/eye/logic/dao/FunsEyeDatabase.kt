package com.funs.eye.logic.dao

/**
 * 应用程序所有Dao操作管理类。
 *
 */
object FunsEyeDatabase {

    private var videoDao: VideoDao? = null

    fun getVideoDao(): VideoDao {
        if (videoDao == null) {
            videoDao = VideoDao()
        }
        return videoDao!!
    }
}