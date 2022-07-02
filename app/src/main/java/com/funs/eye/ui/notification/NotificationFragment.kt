
package com.funs.eye.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.funs.eye.R
import com.funs.eye.event.MessageEvent
import com.funs.eye.event.RefreshEvent
import com.funs.eye.event.SwitchPagesEvent
import com.funs.eye.logic.model.TabEntity
import com.funs.eye.ui.common.ui.BaseViewPagerFragment
import com.funs.eye.ui.notification.push.PushFragment
import com.funs.eye.util.GlobalUtil
import com.flyco.tablayout.listener.CustomTabEntity
import org.greenrobot.eventbus.EventBus

/**
 * 通知主界面
 */
class NotificationFragment : BaseViewPagerFragment() {

    override val createTitles = ArrayList<CustomTabEntity>().apply {
        add(TabEntity(GlobalUtil.getString(R.string.push)))
    }

    override val createFragments: Array<Fragment> = arrayOf(PushFragment.newInstance())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater.inflate(R.layout.fragment_main_container, container, false))
    }

    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)
        if (messageEvent is RefreshEvent && this::class.java == messageEvent.activityClass) {
            when (viewPager?.currentItem) {
                0 -> EventBus.getDefault().post(RefreshEvent(PushFragment::class.java))
            }
        } else if (messageEvent is SwitchPagesEvent) {
            when (messageEvent.activityClass) {
                PushFragment::class.java -> viewPager?.currentItem = 0
            }
        }
    }

    companion object {
        fun newInstance() = NotificationFragment()
    }
}
