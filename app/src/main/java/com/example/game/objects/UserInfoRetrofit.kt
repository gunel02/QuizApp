package com.example.game.objects

import com.example.game.interfaces.InterfaceApi
import com.example.game.objects.Const.BASE_USER_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserInfoRetrofit {
    val api: InterfaceApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_USER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InterfaceApi::class.java)
    }
}