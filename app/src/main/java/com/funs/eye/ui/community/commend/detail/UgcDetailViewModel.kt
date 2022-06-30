package com.funs.eye.ui.community.commend.detail

import androidx.lifecycle.ViewModel
import com.funs.eye.logic.model.CommunityRecommend

class UgcDetailViewModel : ViewModel() {

    var dataList: List<CommunityRecommend.Item>? = null

    var itemPosition: Int = -1
}