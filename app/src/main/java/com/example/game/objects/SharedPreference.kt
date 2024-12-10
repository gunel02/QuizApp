package com.example.game.objects

import android.content.Context
import android.content.SharedPreferences

object SharedPreference {

    private const val SHARED_PREFERENCES_NAME = "MY_SHARED_PREFERENCES"
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPreferences: SharedPreferences

    private const val KEY_LANGUAGE = "language"

    fun init(context: Context) {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun clearSharedPreference() {
        editor.clear().apply()
    }

    fun setLang(phoneNumber: String) {
        editor.putString(KEY_LANGUAGE, phoneNumber).apply()
    }

    fun getLang(): String? {
        return sharedPreferences.getString(KEY_LANGUAGE, "en")
    }

}