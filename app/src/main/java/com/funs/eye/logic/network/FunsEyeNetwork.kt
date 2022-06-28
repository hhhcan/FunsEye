package com.funs.eye.logic.network

import com.funs.eye.logic.network.api.MainPageService

/**
 * 管理所有网络请求。
 *
 * @author can
 */
class FunsEyeNetwork {

    var mainPageService = ServiceCreator.create(MainPageService::class.java)
        private set

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