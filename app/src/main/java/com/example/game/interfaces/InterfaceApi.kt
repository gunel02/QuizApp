package com.example.game.interfaces

import com.example.game.data_classes.user_info.post.PostRequest
import com.example.game.data_classes.user_info.User
import com.example.game.data_classes.user_info.UserInfoModel
import com.example.game.data_classes.user_info.post.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface InterfaceApi {

    @GET("/api/users?page=2")
    suspend fun getUsersInfo():retrofit2.Response<UserInfoModel>



    @GET("/public/v2/users")
    suspend fun getAllUsers():retrofit2.Response<User>


    @POST("/api/users")
    suspend fun postUser(@Body authRequest: PostRequest):retrofit2.Response<UserResponse>

}