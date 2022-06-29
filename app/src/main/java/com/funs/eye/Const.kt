
package com.funs.eye

/**
 * 项目所有全局通用常量的管理类。
 *
 */
interface Const {

    interface Config {

        companion object {

            const val PAGE_SIZE = 50

        }
    }

    interface ActionUrl {

        companion object {

            const val TAG = "eyepetizer://tag/"

            const val DETAIL = "eyepetizer://detail/"

            const val RANKLIST = "eyepetizer://ranklist/"

            const val WEBVIEW = "eyepetizer://webview/?title="

            const val REPLIES_HOT = "eyepetizer://replies/hot?"

            const val TOPIC_DETAIL = "eyepetizer://topic/detail?"

            const val COMMON_TITLE = "eyepetizer://common/?title"

            const val LT_DETAIL = "eyepetizer://lightTopic/detail/"

            const val CM_TOPIC_SQUARE = "eyepetizer://community/topicSquare"

            const val HP_NOTIFI_TAB_ZERO = "eyepetizer://homepage/notification?tabIndex=0"

            const val CM_TAGSQUARE_TAB_ZERO = "eyepetizer://community/tagSquare?tabIndex=0"

            const val CM_TOPIC_SQUARE_TAB_ZERO = "eyepetizer://community/tagSquare?tabIndex=0"

            const val HP_SEL_TAB_TWO_NEWTAB_MINUS_THREE = "eyepetizer://homepage/selected?tabIndex=2&newTabIndex=-3"
        }
    }

    interface Toast {

        companion object {

            const val BIND_VIEWHOLDER_TYPE_WARN = "bindViewHolder Type Unprocessed"
        }
    }
}