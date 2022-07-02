package com.funs.eye.logic.network

import com.funs.eye.logic.network.api.MainPageService
import com.funs.eye.logic.network.api.VideoService

/**
 * 管理所有网络请求。
 *
 * @author can
 */
class FunsEyeNetwork {

    var mainPageService = ServiceCreator.create(MainPageService::class.java)
        private set

    private val videoService = ServiceCreator.create(VideoService::class.java)

    suspend fun fetchVideoBeanForClient(videoId: Long) = videoService.getVideoBeanForClient(videoId)

    suspend fun fetchVideoRelated(videoId: Long) = videoService.getVideoRelated(videoId)

    suspend fun fetchVideoReplies(url: String) = videoService.getVideoReplies(url)


    companion object {

        private var network: FunsEyeNetwork? = null

        fun getInstance(): FunsEyeNetwork {
            if (network == null) {
                synchronized(FunsEyeNetwork::class.java) {
                    if (network == null) {
                        network = FunsEyeNetwork()
                    }
                }
            }
            return network!!
        }
    }
}