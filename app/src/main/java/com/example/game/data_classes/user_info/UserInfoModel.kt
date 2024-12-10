package com.example.game.data_classes.user_info

import com.google.gson.annotations.SerializedName

data class UserInfoModel(
    val page: Int?,
    @SerializedName("per_page")
    val perPage: Int?,
    val total: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    val data: ArrayList<UserInfoData>?,
    val support: UserInfoSupport?)
