package com.ex.notapp.utills

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {

    private lateinit var sharedPreferences: SharedPreferences

    fun unit(context: Context) {
        sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    var firstEnter: Boolean
        get() = sharedPreferences.getBoolean("firstEnter", false)
        set(value) = sharedPreferences.edit().putBoolean("firstEnter", value).apply()
}



