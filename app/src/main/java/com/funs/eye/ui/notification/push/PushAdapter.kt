package com.funs.eye.ui.notification.push

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.funs.eye.R
import com.funs.eye.extension.inflate
import com.funs.eye.extension.load
import com.funs.eye.logic.model.PushMessage
import com.funs.eye.util.ActionUrlUtil
import com.funs.eye.util.DateUtil

class PushAdapter(val fragment: PushFragment) : PagingDataAdapter<PushMessage.Message, PushAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PushAdapter.ViewHolder {
        val holder = ViewHolder(R.layout.item_notification_push.inflate(parent))
        holder.itemView.setOnClickListener {
            getItem(holder.bindingAdapterPosition)?.let {
                ActionUrlUtil.process(fragment, it.actionUrl, it.title ?: "")
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.run {
            holder.ivAvatar.load(icon ?: "") { error(R.mipmap.icon_launcher) }
            holder.tvTitle.text = title
            holder.tvTime.text = DateUtil.getConvertedDate(date)
            holder.tvContent.text = content
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvTime: TextView = view.findViewById(R.id.tvTime)
        val tvContent: TextView = view.findViewById(R.id.tvContent)
        val ivAvatar: ImageView = view.findViewById(R.id.ivAvatar)
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PushMessage.Message>() {
            override fun areItemsTheSame(oldItem: PushMessage.Message, newItem: PushMessage.Message): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PushMessage.Message, newItem: PushMessage.Message): Boolean {
                return oldItem == newItem
            }
        }

    }

}