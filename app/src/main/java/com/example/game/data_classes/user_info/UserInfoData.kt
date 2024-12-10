package com.example.game.data_classes.user_info

import com.google.gson.annotations.SerializedName


data class UserInfoData(
    val id: Int?,
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    val avatar: String?
)
