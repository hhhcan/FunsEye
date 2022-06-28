package com.funs.eye.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.funs.eye.R
import com.funs.eye.event.MessageEvent
import com.funs.eye.event.RefreshEvent
import com.funs.eye.logic.model.TabEntity
import com.funs.eye.ui.common.ui.BaseViewPagerFragment
import com.funs.eye.ui.community.commend.CommendFragment
import com.funs.eye.ui.community.follow.FollowFragment
import com.funs.eye.util.GlobalUtil
import org.greenrobot.eventbus.EventBus

/**
 * 社区主界面
 *
 * @author can
 */

class CommunityFragment : BaseViewPagerFragment() {

    override val createTitles = ArrayList<CustomTabEntity>().apply {
        add(TabEntity(GlobalUtil.getString(R.string.commend)))
        add(TabEntity(GlobalUtil.getString(R.string.follow)))
    }
    override val createFragments: Array<Fragment>
        get() = arrayOf(CommendFragment.newInstance(), FollowFragment.newInstance())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(
            inflater.inflate(
                R.layout.fragment_main_container,
                container,
                false
            )
        )
    }

    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)
        if (messageEvent is RefreshEvent && this::class.java == messageEvent.activityClass) {
            when (viewPager?.currentItem) {
                0 -> EventBus.getDefault().post(RefreshEvent(CommendFragment::class.java))
                1 -> EventBus.getDefault().post(RefreshEvent(FollowFragment::class.java))
            }
        }
    }
}