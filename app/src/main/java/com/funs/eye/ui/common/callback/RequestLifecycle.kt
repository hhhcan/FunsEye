package com.funs.eye.ui.common.callback

/**
 * 在Activity或Fragment中进行网络请求所需要经历的生命周期函数。
 *
 * @author can
 */
interface RequestLifecycle {

    fun startLoading()

    fun loadFinished()

    fun loadFailed(msg: String?)
}