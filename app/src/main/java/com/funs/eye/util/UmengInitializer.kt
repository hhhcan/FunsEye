package com.funs.eye.util

import android.content.Context
import androidx.startup.Initializer
import com.funs.eye.BuildConfig
import com.umeng.commonsdk.UMConfigure

/**
 * App Startup
 *
 */
class UmengInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        UMConfigure.init(context, UMConfigure.DEVICE_TYPE_PHONE, null)
        UMConfigure.setLogEnabled(BuildConfig.DEBUG)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()

}