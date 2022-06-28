package com.funs.eye.logic.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cover(
    val blurred: String,
    val detail: String,
    val feed: String,
    val homepage: String?,
    val sharing: String?
) :
    Parcelable

@Parcelize
data class Consumption(
    val collectionCount: Int,
    val realCollectionCount: Int,
    val replyCount: Int,
    val shareCount: Int
) :
    Parcelable


data class Tag(
    val actionUrl: String,
    val adTrack: Any,
    val bgPicture: String,
    val childTagIdList: Any,
    val childTagList: Any,
    val communityIndex: Int,
    val desc: String,
    val haveReward: Boolean,
    val headerImage: String,
    val id: Int,
    val ifNewest: Boolean,
    val name: String,
    val newestEndTime: Any,
    val tagRecType: String
)

data class Label(val actionUrl: String?, val text: String?, val card: String, val detail: Any?)
