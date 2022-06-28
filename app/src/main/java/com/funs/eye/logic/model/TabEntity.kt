package com.funs.eye.logic.model

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * 与CommonTabLayout搭配使用的实体类
 */
class TabEntity(
    private var title: String,
    private val selectedIcon: Int = 0,
    private var unSelectedIcon: Int = 0
) : CustomTabEntity {
    override fun getTabTitle(): String {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}