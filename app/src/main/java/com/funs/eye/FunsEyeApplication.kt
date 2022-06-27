package com.funs.eye

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

/**
 * 自定义Application，进行全局的初始化操作。
 *
 * @author can
 */
class FunsEyeApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        context = this;
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    //Android 5.0 以上默认内置分包处理，可忽略不写
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}