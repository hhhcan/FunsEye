package com.funs.eye.ui.common.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.funs.eye.R
import com.funs.eye.extension.invisible
import com.funs.eye.extension.load
import com.funs.eye.extension.visible
import com.funs.eye.logic.model.Discovery
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class HorizontalScrollCardAdapter : BaseBannerAdapter<Discovery.ItemX, HorizontalScrollCardAdapter.ViewHolder>() {

        inner class ViewHolder(val view: View) : BaseViewHolder<Discovery.ItemX>(view) {

            override fun bindData(item: Discovery.ItemX, position: Int, pageSize: Int) {
                val ivPicture = findView<ImageView>(R.id.ivPicture)
                val tvLabel = findView<TextView>(R.id.tvLabel)
                if (item.data.label?.text.isNullOrEmpty()) tvLabel.invisible() else tvLabel.visible()
                tvLabel.text = item.data.label?.text ?: ""
                ivPicture.load(item.data.image, 4f)
            }
        }

        override fun getLayoutId(viewType: Int): Int {
            return R.layout.item_banner_item_type
        }

        override fun createViewHolder(view: View, viewType: Int): ViewHolder {
            return ViewHolder(view)
        }

        override fun onBind(holder: ViewHolder, data: Discovery.ItemX, position: Int, pageSize: Int) {
            holder.bindData(data, position, pageSize)
        }
    }