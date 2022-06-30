package com.funs.eye.logic.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

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


@Parcelize
data class Author(
    val adTrack: @RawValue Any?,
    val approvedNotReadyVideoCount: Int,
    val description: String,
    val expert: Boolean,
    val follow: Follow,
    val icon: String?,
    val id: Int,
    val ifPgc: Boolean,
    val latestReleaseTime: Long,
    val link: String,
    val name: String,
    val recSort: Int,
    val shield: Shield,
    val videoNum: Int
) : Parcelable {

    @Parcelize
    data class Follow(val followed: Boolean, val itemId: Int, val itemType: String) : Parcelable

    @Parcelize
    data class Shield(val itemId: Int, val itemType: String, val shielded: Boolean) : Parcelable
}

@Parcelize
data class WebUrl(val forWeibo: String, val raw: String) : Parcelable

data class Provider(val alias: String, val icon: String, val name: String)