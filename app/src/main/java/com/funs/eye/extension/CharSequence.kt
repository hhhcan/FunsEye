
package com.funs.eye.extension

import android.widget.Toast
import com.funs.eye.FunsEyeApplication
import com.funs.eye.ui.common.ui.vassonic.SonicRuntimeImpl
import com.tencent.sonic.sdk.SonicConfig
import com.tencent.sonic.sdk.SonicEngine
import com.tencent.sonic.sdk.SonicSessionConfig

/**
 * 弹出Toast提示。
 *
 * @param duration 显示消息的时间  Either {@link #LENGTH_SHORT} or {@link #LENGTH_LONG}
 */
fun CharSequence.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(FunsEyeApplication.context, this, duration).show()
}

/**
 * VasSonic预加载session。
 *
 * @param CharSequence 预加载url
 */
fun CharSequence.preCreateSession(): Boolean {
    if (!SonicEngine.isGetInstanceAllowed()) {
        SonicEngine.createInstance(SonicRuntimeImpl(FunsEyeApplication.context), SonicConfig.Builder().build())
    }
    val sessionConfigBuilder = SonicSessionConfig.Builder().apply { setSupportLocalServer(true) }
    val preloadSuccess = SonicEngine.getInstance().preCreateSession(this.toString(), sessionConfigBuilder.build())
    logD("preCreateSession()", "${this}\t:${if (preloadSuccess) "Preload start up success!" else "Preload start up fail!"}")
    return preloadSuccess
}