package com.example.game.app

import android.app.Application
import com.example.game.objects.SharedPreference

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreference.init(this@App)
    }
}