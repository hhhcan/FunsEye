package com.funs.eye.ui.community.follow

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.funs.eye.BuildConfig
import com.funs.eye.Const
import com.funs.eye.R
import com.funs.eye.extension.*
import com.funs.eye.logic.model.Follow
import com.funs.eye.ui.common.holder.EmptyViewHolder
import com.funs.eye.ui.newdetail.NewDetailActivity
import com.funs.eye.util.DateUtil
import com.funs.eye.util.GlobalUtil
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer

class FollowAdapter(private val fragment: FollowFragment) :
    PagingDataAdapter<Follow.Item, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun getItemCount() = super.getItemCount()

    override fun getItemViewType(position: Int) = when {
        getItem(position)!!.type == "autoPlayFollowCard"
                && getItem(position)!!.data.dataType == "FollowCard" -> AUTO_PLAY_FOLLOW_CARD
        else -> Const.ItemViewType.UNKNOWN
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        AUTO_PLAY_FOLLOW_CARD -> AutoPlayFollowCardViewHolder(
            R.layout.item_community_auto_play_follow_card_follow_card_type.inflate(
                parent
            )
        )
        else -> EmptyViewHolder(View(parent.context))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AutoPlayFollowCardViewHolder -> {
                val item = getItem(position)!!
                item.data.content.data.run {
                    holder.ivAvatar.load(item.data.header.icon ?: author?.icon ?: "")

                    holder.tvReleaseTime.text = DateUtil.getDate(
                        releaseTime ?: author?.latestReleaseTime ?: System.currentTimeMillis(),
                        "HH:mm"
                    )
                    holder.tvTitle.text = title
                    holder.tvNickname.text = author?.name ?: ""
                    holder.tvContent.text = description
                    holder.tvCollectionCount.text = consumption.collectionCount.toString()
                    holder.tvReplyCount.text = consumption.replyCount.toString()
                    holder.tvVideoDuration.visible()    //视频播放后，复用tvVideoDuration直接隐藏了
                    holder.tvVideoDuration.text = duration.conversionVideoDuration()
                    startAutoPlay(
                        fragment.activity,
                        holder.videoPlayer,
                        position,
                        playUrl,
                        cover.feed,
                        TAG,
                        object : GSYSampleCallBack() {
                            override fun onPrepared(url: String?, vararg objects: Any?) {
                                super.onPrepared(url, *objects)
                                holder.tvVideoDuration.gone()
                                GSYVideoManager.instance().isNeedMute = true
                            }

                            override fun onClickResume(url: String?, vararg objects: Any?) {
                                super.onClickResume(url, *objects)
                                holder.tvVideoDuration.gone()
                            }

                            override fun onClickBlank(url: String?, vararg objects: Any?) {
                                super.onClickBlank(url, *objects)
                                holder.tvVideoDuration.visible()
                                NewDetailActivity.start(
                                    fragment.activity,
                                    NewDetailActivity.VideoInfo(
                                        id,
                                        playUrl,
                                        title,
                                        description,
                                        category,
                                        library,
                                        consumption,
                                        cover,
                                        author!!,
                                        webUrl
                                    )
                                )
                            }
                        })
                    holder.let {
                        setOnClickListener(
                            it.videoPlayer.thumbImageView,
                            it.itemView,
                            it.ivCollectionCount,
                            it.tvCollectionCount,
                            it.ivFavorites,
                            it.tvFavorites,
                            it.ivShare
                        )
                        {
                            when (this) {
                                it.videoPlayer.thumbImageView, it.itemView -> {
                                    NewDetailActivity.start(
                                        fragment.activity, NewDetailActivity.VideoInfo(
                                            item.data.content.data.id,
                                            playUrl,
                                            title,
                                            description,
                                            category,
                                            library,
                                            consumption,
                                            cover,
                                            author!!,
                                            webUrl
                                        )
                                    )
                                }
                                it.ivCollectionCount, it.tvCollectionCount, it.ivFavorites, it.tvFavorites -> {
//                                    LoginActivity.start(fragment.activity)
                                }
                                it.ivShare -> {
                                    showDialogShare(fragment.activity, getShareContent(item))
                                }
                            }
                        }
                    }
                }
            }
            else -> {
                holder.itemView.gone()
                if (BuildConfig.DEBUG) "${TAG}:${Const.Toast.BIND_VIEWHOLDER_TYPE_WARN}\n${holder}".showToast()
            }
        }
    }

    private fun getShareContent(item: Follow.Item): String {
        item.data.content.data.run {
            val linkUrl =
                "${item.data.content.data.webUrl.raw}&utm_campaign=routine&utm_medium=share&utm_source=others&uid=0&resourceType=${resourceType}"
            return "${title}|${GlobalUtil.appName}：\n${linkUrl}"
        }
    }

    inner class AutoPlayFollowCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivAvatar = view.findViewById<ImageView>(R.id.ivAvatar)
        val tvReleaseTime = view.findViewById<TextView>(R.id.tvReleaseTime)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvNickname = view.findViewById<TextView>(R.id.tvNickname)
        val tvContent = view.findViewById<TextView>(R.id.tvContent)
        val ivCollectionCount = view.findViewById<ImageView>(R.id.ivCollectionCount)
        val tvCollectionCount = view.findViewById<TextView>(R.id.tvCollectionCount)
        val ivReply = view.findViewById<ImageView>(R.id.ivReply)
        val tvReplyCount = view.findViewById<TextView>(R.id.tvReplyCount)
        val ivFavorites = view.findViewById<ImageView>(R.id.ivFavorites)
        val tvFavorites = view.findViewById<TextView>(R.id.tvFavorites)
        val tvVideoDuration = view.findViewById<TextView>(R.id.tvVideoDuration)
        val ivShare = view.findViewById<ImageView>(R.id.ivShare)
        val videoPlayer: GSYVideoPlayer = view.findViewById<GSYVideoPlayer>(R.id.videoPlayer)
    }

    companion object {
        const val TAG = "FollowAdapter"
        const val AUTO_PLAY_FOLLOW_CARD =
            Const.ItemViewType.MAX    //type:autoPlayFollowCard -> dataType:FollowCard

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Follow.Item>() {
            override fun areItemsTheSame(oldItem: Follow.Item, newItem: Follow.Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Follow.Item, newItem: Follow.Item): Boolean {
                return oldItem == newItem
            }
        }

        fun startAutoPlay(
            activity: Activity,
            player: GSYVideoPlayer,
            position: Int,
            playUrl: String,
            coverUrl: String,
            playTag: String,
            callBack: GSYSampleCallBack? = null
        ) {
            player.run {
                //防止错位设置
                setPlayTag(playTag)
                //设置播放位置防止错位
                setPlayPosition(position)
                //音频焦点冲突时是否释放
                setReleaseWhenLossAudio(false)
                //设置循环播放
                setLooping(true)
                //增加封面
                val cover = ImageView(activity)
                cover.scaleType = ImageView.ScaleType.CENTER_CROP
                cover.load(coverUrl, 4f)
                cover.parent?.run { removeView(cover) }
                setThumbImageView(cover)
                //设置播放过程中的回调
                setVideoAllCallBack(callBack)
                //设置播放URL
                setUp(playUrl, false, null)
            }
        }
    }
}